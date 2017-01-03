package com.sosxsos.ssm.entity;

import java.io.Serializable;

public class StatisticsEntity implements Serializable {
	
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
private int	zoned;
private int		reported;
private int		agree;
private int		success;
private int		recycle;
private int	video;
private int	user_id;

}
