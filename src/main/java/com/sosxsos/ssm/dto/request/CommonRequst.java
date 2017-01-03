package com.sosxsos.ssm.dto.request;

public class CommonRequst {
private long banana_id;
public long getBanana_id() {
	return banana_id;
}
public void setBanana_id(long banana_id) {
	this.banana_id = banana_id;
}
public boolean isZone() {
	return zone;
}
public void setZone(boolean zone) {
	this.zone = zone;
}
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
public boolean isAgree() {
	return agree;
}
public void setAgree(boolean agree) {
	this.agree = agree;
}
public int getCancel_reason() {
	return cancel_reason;
}
public void setCancel_reason(int cancel_reason) {
	this.cancel_reason = cancel_reason;
}
private boolean zone;
private String code;
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
private String time;
private String place;
private boolean agree;

private int cancel_reason;
private String email;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


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






}
