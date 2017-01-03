package com.sosxsos.ssm.entity;

public class LocationRangeEntity{
	public 	LocationRangeEntity(double minLat,double  minLng, double maxLat, double maxLng){
		this.setMaxLat(maxLat);
		this.setMaxLng(maxLng);
		this.setMinLat(minLat);
		this.setMinLng(minLng);
	}
		private double minLat; public double getMinLat() {
			return minLat;
		}
		public void setMinLat(double minLat) {
			this.minLat = minLat;
		}
		public double getMinLng() {
			return minLng;
		}
		public void setMinLng(double minLng) {
			this.minLng = minLng;
		}
		public double getMaxLat() {
			return maxLat;
		}
		public void setMaxLat(double maxLat) {
			this.maxLat = maxLat;
		}
		public double getMaxLng() {
			return maxLng;
		}
		public void setMaxLng(double maxLng) {
			this.maxLng = maxLng;
		}
		private double minLng; private double maxLat;private double maxLng;private String current_time;private String phone;
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getCurrent_time() {
			return current_time;
		}
		public void setCurrent_time(String current_time) {
			this.current_time = current_time;
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
	
	
		private double current_lat;
		private double current_lng;
		
		
		
		public String getVerification_code() {
			return verification_code;
		}
		public void setVerification_code(String verification_code) {
			this.verification_code = verification_code;
		}
	
		private String verification_code;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		private String type;
		
		private int id;

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	}