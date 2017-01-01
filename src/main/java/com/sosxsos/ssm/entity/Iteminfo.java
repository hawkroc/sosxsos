package com.sosxsos.ssm.entity;

public class Iteminfo {
//	 “name”: string,
//     “desc”: string,
//     “price”: double
	//    currencty string  人民币	CNY 美元	USD  新西兰元	NZD 澳大利亚元	AUD
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private String desc;
	private double price;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	private String  currency;
}
