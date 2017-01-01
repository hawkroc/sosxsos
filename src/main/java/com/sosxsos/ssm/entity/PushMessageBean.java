package com.sosxsos.ssm.entity;

public class PushMessageBean {
private String to;
public String getTo() {
	return to;
}


public void setTo(String to) {
	this.to = to;
}


public MessageData getData() {
	return data;
}


public void setData(MessageData data) {
	this.data = data;
}


private MessageData data;


public class MessageData{
	private String action;

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMessagebody() {
		return messagebody;
	}
	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}
	private String messagebody;
}
}
