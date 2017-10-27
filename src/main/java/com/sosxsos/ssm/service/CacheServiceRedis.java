package com.sosxsos.ssm.service;


public interface CacheServiceRedis {

	/**
	 * 
	 * @param code
	 * @return
	 */
	boolean checkCodeByFrontEnd(int code);

}