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
}
