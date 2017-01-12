package com.fl.elasticsearch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
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
		CriteriaQuery criteriaQuery = new CriteriaQuery(
				new Criteria("MANDT").contains("654983120915364413160024"));
		// when
		WlmMatch sampleEntity1 = elasticsearchTemplate
				.queryForObject(criteriaQuery, WlmMatch.class);
		// then
		assertThat(sampleEntity1, is(notNullValue()));
		List<WlmMatch> list = repository
				.findByMandt("654983120915364413160024");
		assertThat(list, is(notNullValue()));
		// List<WlmMatch> list = repository
		// .findByMANDT("654983120915364413160024");
		// for (int i = 0; i < list.size(); i++) {
		// WlmMatch wlmMatch = list.get(i);
		// System.out.println(wlmMatch.getId());
		// }
	}
}
