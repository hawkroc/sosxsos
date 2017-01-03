package com.sosxsos.ssm.entity;

import java.io.Serializable;

public class BubbleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6188356815325263821L;

	/**
	 * “ “thought”: { “topic”: string, “key_word”: string }
	 * 
	 * 
	 */
	private int id;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	private String key_word;

	private String topic;
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

	private String video_url;
	private String image_url;

}
