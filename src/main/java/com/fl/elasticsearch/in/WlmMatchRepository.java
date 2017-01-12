package com.fl.elasticsearch.in;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fl.elasticsearch.WlmMatch;

public interface WlmMatchRepository
		extends PagingAndSortingRepository<WlmMatch, String> {
	public List<WlmMatch> findByMandt(String MANDT);
	
}
