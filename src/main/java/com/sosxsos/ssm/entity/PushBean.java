package com.sosxsos.ssm.entity;
//
//{
//	  "push_system": int,
//	  "push_token": string,
//	}

public class PushBean {
private int push_system;
public int getPush_system() {
	return push_system;
}
public void setPush_system(int push_system) {
	this.push_system = push_system;
}
public String getPush_token() {
	return push_token;
}
public void setPush_token(String push_token) {
	this.push_token = push_token;
}
private String push_token;
}
