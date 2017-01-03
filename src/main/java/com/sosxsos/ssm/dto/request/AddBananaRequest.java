package com.sosxsos.ssm.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.sosxsos.ssm.entity.BananaEntity;

//hkhkhu
public class AddBananaRequest {
//	{
//	      “action_type”: int,
//	      “banana”: {
//	            “bubble”: {
//	                   “topic”: int,
//	                   “key_word”: string
//	             }  
//	            “product: {
//	                  “selling_reason”: int,
//	                  “item_info”: {
//	                         “name”: string,
//	                          “desc”: string,
//	                          “price”: double,
//	                          “currency”:string
//	                    },
//	                    “tags”: [{
//	                          “tag”: int,
//	                          “value”: string,
//	                          “desc”: string
//	                      },
//	                      {
//	                          “tag”: int,
//	                          “value”: string,
//	                          “desc”: string
//	                      }]
//	               }
//	        }
//	}
	
	 private MultipartFile video;
	 

	 public MultipartFile getVideo() {
		return video;
	}
	public void setVideo(MultipartFile video) {
		this.video = video;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

	private MultipartFile image;
	 
	 
public int getAction_type() {
	return action_type;
}
public void setAction_type(int action_type) {
	this.action_type = action_type;
}

	private int action_type;
	public BananaEntity getBanana() {
		return banana;
	}
	public void setBanana(BananaEntity banana) {
		this.banana = banana;
	}

	private BananaEntity banana;
	
	
}
