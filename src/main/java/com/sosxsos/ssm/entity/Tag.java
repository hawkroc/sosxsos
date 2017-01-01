package com.sosxsos.ssm.entity;

public class Tag extends BaseEntity{
//	  tag: int,
//      value1: string,
//      value2: string,
//      desc: string.
	
	private int tag;
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String value ;
	private String desc ;
	
	

}
