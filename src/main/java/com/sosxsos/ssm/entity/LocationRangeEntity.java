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
	}