package com.sosxsos.ssm.entity;

public class Threading {
//	"time": "string",
//	"place": "string"
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	private String place;
	
	
	public int getShareByTimes() {
		return shareByTimes;
	}
	public void setShareByTimes(int shareByTimes) {
		this.shareByTimes = shareByTimes;
	}
	public int getGetByTimes() {
		return getByTimes;
	}
	public void setGetByTimes(int getByTimes) {
		this.getByTimes = getByTimes;
	}
	private int shareByTimes;
	private int getByTimes;
	
	
}
