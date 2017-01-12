package com.fl.elasticsearch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fl.elasticsearch.in.WlmMatchRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-elasticsearch.xml")
public class QueryWlmMatchTests {
	@Autowired
	private WlmMatchRepository repository;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void shouldSupportAND() {
		// given
		
		// when
		
		// then
		// CriteriaQuery criteriaQuery = new CriteriaQuery(
		// new Criteria("MANDT").contains("654983120915364413160024"));
		// when
		// String queryString = "{\"bool\" : {\"must\" : {\"term\" : {\"MANDT\"
		// : \"654983120915364413160024\"}}}}";
		
		QueryBuilder query = QueryBuilders.boolQuery().must(
				QueryBuilders.termQuery("MANDT", "654983120915364413160024"));
		NativeSearchQuery qb = new NativeSearchQuery(query);
		WlmMatch sampleEntity1 = (WlmMatch) elasticsearchTemplate.query(qb,
				new ResultsExtractor() {
					
					@Override
					public Object extract(SearchResponse paramSearchResponse) {
						SearchHits hits = paramSearchResponse.getHits();
						long count = hits.getTotalHits();
						SearchHit[] hitArray = hits.getHits();
						WlmMatch wlmMatch = new WlmMatch();
						for (int i = 0; i < count; i++) {
							SearchHit hit = hitArray[i];
							Map<String, Object> fields = hit.getSource();
							for (String key : fields.keySet()) {
								System.out.println(key);
								System.out.println(fields.get(key));
								if (key.equals("MATNR")) {
									wlmMatch.setMATNR(fields.get(key) + "");
								}
							}
						}
						return wlmMatch;
					}
				});
		// // then
		// assertThat(sampleEntity1, is(notNullValue()));
		// List<WlmMatch> list = repository
		// .findByMandt("654983120915364413160024");
		assertThat(sampleEntity1, is(notNullValue()));
		// List<WlmMatch> list = repository
		// .findByMANDT("654983120915364413160024");
		// for (int i = 0; i < list.size(); i++) {
		// WlmMatch wlmMatch = list.get(i);
		// System.out.println(wlmMatch.getId());
		// }
	}
}
