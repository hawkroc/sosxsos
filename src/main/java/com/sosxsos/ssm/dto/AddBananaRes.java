package com.sosxsos.ssm.dto;

public class AddBananaRes {
	

//	{
//	    “banana_id”: long,
//	    “num_zones”: int,
//	    “num_reason”: int，
//	    ”video_url“:string,
//	    “image_url”:string
//	}
//	
	
	

	public int getNum_zones() {
		return num_zones;
	}
	public void setNum_zones(int num_zones) {
		this.num_zones = num_zones;
	}
	public int getNum_reason() {
		return num_reason;
	}
	public void setNum_reason(int num_reason) {
		this.num_reason = num_reason;
	}
	
	
	private int num_zones;
	private int num_reason;
	private long banana_id;
	
//    ”video_url“:string,
//    “image_url”:string
	private String video_url;
	public long getBanana_id() {
		return banana_id;
	}
	public void setBanana_id(long banana_id) {
		this.banana_id = banana_id;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	private String image_url;
	


}
