package com.sosxsos.ssm.util;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonUtil {
	
	public static String beanToJson(Object bean)  {
		ObjectMapper m = new ObjectMapper();  
		String value=null;
		try {
			value = m.writeValueAsString(bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return value;
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
//		Iteminfo p = new Iteminfo();
//		p.setDesc("dsfdsfdsf");
//		p.setName("name");
//		p.setPrice(45.0);
//		System.out.println(JsonUtil.beanToJson(p));
//		

	}
}
