package com.sosxsos.ssm.entity;


public class UserEntity {
//`id`, `phone`, `type`, `lon`, `lat`, `status`
	//"verified_id": boolean,
	 // "verified_email": string,
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private String phone;
	public boolean isVerified_id() {
		return verified_id;
	}
	public void setVerified_id(boolean verified_id) {
		this.verified_id = verified_id;
	}
	public String getVerified_email() {
		return verified_email;
	}
	public void setVerified_email(String verified_email) {
		this.verified_email = verified_email;
	}
	private boolean  verified_id;
	private String verified_email;
	private int type;
	private double lon;
	private double 	lat;
	private int status;
	public StatisticsEntity getStatisticsEntity() {
		this.statisticsEntity.setAgree(this.agree);
		this.statisticsEntity.setSuccess(this.success);
		this.statisticsEntity.setRecycle(this.recycle);
		this.statisticsEntity.setReported(this.reported);
		this.statisticsEntity.setVideo(this.video);
		this.statisticsEntity.setUser_id(this.id);
		return statisticsEntity;
	}
	public void setStatisticsEntity(StatisticsEntity statisticsEntity) {
		this.statisticsEntity = statisticsEntity;
	}
	private StatisticsEntity statisticsEntity;
	private int push_type;
	public int getPush_type() {
		return push_type;
	}
	public void setPush_type(int push_type) {
		this.push_type = push_type;
	}
	public String getPush_token() {
		return push_token;
	}
	public void setPush_token(String push_token) {
		this.push_token = push_token;
	}
	private String push_token;
	
	
	public int getZoned() {
		return zoned;
	}
	public void setZoned(int zoned) {
		this.zoned = zoned;
	}
	public int getReported() {
		return reported;
	}
	public void setReported(int reported) {
		this.reported = reported;
	}
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getRecycle() {
		return recycle;
	}
	public void setRecycle(int recycle) {
		this.recycle = recycle;
	}
	public int getVideo() {
		return video;
	}
	public void setVideo(int video) {
		this.video = video;
	}
	private int	zoned;
	private int		reported;
	private int		agree;
	private int		success;
	private int		recycle;
	private int	video;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	private int code;
	
	
	
	
}
