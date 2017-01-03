package com.sosxsos.ssm.entity;

import java.util.List;


import com.sosxsos.ssm.util.JacksonUtil;

public class ProductEntity {
private Iteminfo Item_info;

public Iteminfo getItem_info() {
	return Item_info;
}
public void setItem_info(Iteminfo item_info) {
	Item_info = item_info;
}
private String productInfoByJson;

public String getProductInfoByJson() {
	String json=null;
	if(this.Item_info!=null){
	json= JacksonUtil.toJSon(this.Item_info);
	}
return json;
	
}
public String getTagsByJson() {
	String json=null;
	if(this.tags!=null){
	json= JacksonUtil.toJSon(this.tags);
	}
return json;
}
private String tagsByJson;

//public String getProductInfoByJson(){
//	String json=null;
//	if(this.product_info!=null){
//	json= JsonUtil.beanToJson(this.product_info);
//	}
//return json;
//	
//}
//
//public String getTagsByJson(){
//	String json=null;
//	if(this.tags!=null){
//	json= JsonUtil.beanToJson(this.tags);
//	}
//return json;
//	
//}




private int id ;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public List<Tag> getTags() {
	return tags;
}
public void setTags(List<Tag> tags) {
	this.tags = tags;
}
public String getImage_url() {
	return image_url;
}
public void setImage_url(String image_url) {
	this.image_url = image_url;
}
public int getSelling_reason() {
	return selling_reason;
}
public void setSelling_reason(int selling_reason) {
	this.selling_reason = selling_reason;
}
private List<Tag> tags;
private String image_url;
private int selling_reason;
private String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
private double price;


}
