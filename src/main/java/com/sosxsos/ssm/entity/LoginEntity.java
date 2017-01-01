package com.sosxsos.ssm.entity;



public class LoginEntity {
	
	
//	 “phone”: string,
//     “password”: string,
//“current_lat”: double,
//     “current_lng”: double

	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getCurrent_lat() {
		return current_lat;
	}
	public void setCurrent_lat(double current_lat) {
		this.current_lat = current_lat;
	}
	public double getCurrent_lng() {
		return current_lng;
	}
	public void setCurrent_lng(double current_lng) {
		this.current_lng = current_lng;
	}
	private String password;
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	private String user_token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	private double current_lat;
	private double current_lng;



}
