package com.sosxsos.ssm.entity;

import java.io.Serializable;

public class MediaEntity  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
