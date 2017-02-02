package com.sosxsos.ssm.util;

import java.util.Timer;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Test {
	public static String getString(){
		try   
		{
			System.out.println("456");
		Thread.currentThread().sleep(10000);//毫秒   
		System.out.println("123");
		
		}   
		catch(Exception e){
			e.printStackTrace();
		}  
		
		return "this is be invoked thread sleep";
		
		
	}
	
	
}
