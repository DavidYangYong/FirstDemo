package com.fl.kafka;

public class ProducerData {
	private String id;
	private String user;
	private String keyword;
	private String currentDate;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	@Override
	public String toString() {
		String keyword = "[info kafka producer:]";
		keyword = keyword + this.getId() + "-" + this.getUser() + "-"
				+ this.getKeyword() + "-" + this.getCurrentDate();
		return keyword;
	}
}
