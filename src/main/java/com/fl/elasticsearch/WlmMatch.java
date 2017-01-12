package com.fl.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(
		indexName = "third.wlm_match", type = "third.wlm_match", shards = 5,
		replicas = 1, refreshInterval = "-1")
public class WlmMatch {
	
	private String id;
	
	private String mandt;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMandt() {
		return mandt;
	}
	
	public void setMandt(String mandt) {
		this.mandt = mandt;
	}
	
}
