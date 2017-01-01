package com.sosxsos.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.sosxsos.ssm.entity.PushMessageBean;
import com.google.firebase.FirebaseOptions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service("pushNotificationService")
public class PushNotificationService {

	FirebaseOptions options = null;
	private final String contentKey = "Content-Type";
	private final String contentName = "application/json";

	/**
	 * 
	 * @throws UnirestException
	 */
	public void pushMessage(PushMessageBean message) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post("https://fcm.googleapis.com/fcm/send")
				.header(contentKey, contentName).body(message).asJson();
		// Unirest.
		System.out.println(response.getBody().getArray().toString());

	}

	static String key = "cmyTYjk0PlM:APA91bGRJwN-53L6RSpHiUCpJuk7at969T9erYJGeX0_SYfRaQ_eREM2P71t7lS0u4eM0K-3UAYQkCXShwDqZuj4wWkN6hMlsaVuL1tmfrt0u5hnUi9-Cp7mufAngINK0BvBlEfNPY8S";

	//static String server_key = "AAAAI7XCZBk:APA91bGIsZn5vYjRg4r15AIO25Or4jVBSFDjiHxBgeP8K6DP8eUCRzW2pCDIHwmVgZq4CIaU-MY9jDv8W6qLflqQqOYu-5CS3M1-d2KLpjshtrMV_wgl90q22gpSluR3Yw0tOy4YsvS9clgc1spWNXgmyar3OYBZVg";
	static String Authorization = "key=AIzaSyAJ6M6y2O81iJDhhGWcbzH7b788R3Zdj_k";

	/**
	 * 1.zoning_requests 2.
	 * 
	 * @param message
	 * @throws UnirestException
	 */
	public void pushNotifiction(PushMessageBean message) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post("https://fcm.googleapis.com/fcm/send")
				.header("Authorization", Authorization).header(contentKey, contentName).body(message).asJson();

		System.out.println(response.getBody().getArray().toString());

		// push message

	}

	//
	// After spending some hours I found that in Postman you have to put the
	// following in the Headers.
	//
	// Key: Content-Type Value: application/json
	// Key: Authorization Value:
	// key=AAAAfnYrKvU:APA91bFwgeM3zuFId6UDzvIHk9qZ3lKHnX-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	// (Firebase Cloud Messaging token)
	//
	// Then click Body and select Raw, here you add the json.
	//
	// { "data": {
	// "title": "new messages"
	// "score": "5x1",
	// "time": "15:10"
	// }, "to": "/topics/alldevices" }
	//
	// I also found that you cannot send to all devices by eliminating the "to":
	// You will have to have your app subscribe to a topic. In my case I made my
	// app subscribed to "alldevices".
	//
	// Now I can send "to":"/topics/alldevices" and all apps will receive the
	// notification.
	//
	//

}
