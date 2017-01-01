package com.sosxsos.ssm.service.impl;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



@Service("smsService")
public class SmsService implements Runnable {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC2454168be8ba0cd3b5ee549aee80073b";
  public static final String AUTH_TOKEN = "6216791c2bb1f2e6db2cf5d7f6b4dea3";
  
  
 public SmsService(){
	  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
 }
 
 public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

private String phone;
 private String content;
 /**
  * 
  * @param phone
  * @param content
  */
  private void sendMessage(){
	  System.out.println(content+phone);
	  Message message = Message.creator(new PhoneNumber(phone), new PhoneNumber("+61451266222"),
			  content).create();
  }
@Override
public void run() {
	// TODO Auto-generated method stub
	this.sendMessage();
	
}

//  public static void main(String[] args) {
//    //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//    Message message = Message
//        .creator(new PhoneNumber("+8618665016206"), new PhoneNumber("+640212923526"),
//            "This is test message ?")
//      //  .setMediaUrl("https://c1.staticflickr.com/3/2899/14341091933_1e92e62d12_b.jpg")
//        .create();
//
//    System.out.println(message.getSid());
//  }
}