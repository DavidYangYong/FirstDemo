package com.fl.elasticsearch.in;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fl.elasticsearch.WlmMatch;

public interface WlmMatchRepository
		extends ElasticsearchRepository<WlmMatch, String> {
	@Query("{\"bool\" : {\"must\" : {\"term\" : {\"MANDT\" : \"?0\"}}}}")
	public List<WlmMatch> findByMandt(String MANDT);
	
}
