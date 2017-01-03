package com.sosxsos.ssm.entity;

import java.io.Serializable;

public class ResidentEntity implements Serializable{

//{
//“residents”: [{
//       
//          “phone”: string,
//          “latitude”: double,
//          “longitude”: double, 
//          “banana”: {
//            “bubble”: {
//                   “topic”: int,
//                   “key_word”: string
//             }
//            “video_url”: string,
//            “product: {
//                  “image_url”: string,
//                  “selling_reason”: int,
//                  “item_info”: {
//                         “name”: string,
//                          “desc”: string,
//                          “price”: double
//                    },
//                    “tags”: [{
//                          “tag”: int,
//                          “value”: string,
//                          “desc”: string
//                      },
//                      {
//                          “tag”: int,
//                          “value”: string,
//                          “desc”: string
//                      }]
//               }
//        }}]
//}

	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public BananaEntity getBanana() {
		return banana;
	}
	public void setBanana(BananaEntity banana) {
		this.banana = banana;
	}
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


	private BananaEntity banana;

	private double latitude;
	private double longitude;
}
