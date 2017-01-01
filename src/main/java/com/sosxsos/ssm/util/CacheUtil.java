package com.sosxsos.ssm.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	public static void cacheSave(Object key,Object o,String cache_name){

		getCache(cache_name).put(new Element(key,o));	
	
		 
		 
	}

	public static void removeCache(Object key,String cache_name){
		getCache(cache_name).remove(key);	
		 
		 
	}
	
	public static void updateCache(Object key,Object o,String cache_name) {
		getCache(cache_name).remove(key);
		getCache(cache_name).put(new Element(key,o));
	}
	
	
	public static Element getCacheObject(Object key,String cache_name){
		return getCache(cache_name).get(key);	
		 
		 
	}
	
	private static Cache getCache(String cache_name) {
		return  CacheManager.getInstance().getCache(cache_name);
		
	}
	
	
	
	public static Object getBananaFromCache(Object key,String cache_name,Class<?>... elementClasses) {
		Element o = CacheUtil.getCacheObject(key, cache_name);
		Class<?> rs= null;
		if (o != null) {
			rs = (Class<?>) o.getObjectValue();
		}
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
}
