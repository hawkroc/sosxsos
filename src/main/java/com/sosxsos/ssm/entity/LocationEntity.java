package com.sosxsos.ssm.entity;

public class LocationEntity {
/**
 * {
    "latitude":double,
    "longitude": double,
    "accuracy": double
}
 */
	
	
	private double latitude;
	public double getLatitude() {
	return latitude;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
public double getLongitude() {
	return longitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public double getAccuracy() {
	return accuracy;
}
public void setAccuracy(double accuracy) {
	this.accuracy = accuracy;
}
	private double longitude;
	private double accuracy;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String phone;
	
}
