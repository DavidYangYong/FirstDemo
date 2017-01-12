package com.fl.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

@Document(
		indexName = "third.wlm_match", type = "third.wlm_match", shards = 5,
		replicas = 1, refreshInterval = "-1")
public class WlmMatch {
	@Id
	@Field(index = FieldIndex.not_analyzed, store = true)
	private String id;
	private String mandt;
	private String MATNR;
	private String source;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getMATNR() {
		return MATNR;
	}
	
	public void setMATNR(String MATNR) {
		MATNR = MATNR;
	}
	
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
