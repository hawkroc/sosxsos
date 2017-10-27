package com.sosxsos.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sosxsos.ssm.cache.RedisCache;
import com.sosxsos.ssm.util.CacheUtil;
import com.sosxsos.ssm.util.Const;

import net.sf.ehcache.Element;

@Service("cacheService")
public class CacheService {
	// userCache
	// userCacheEntity
/**
 * 
 * @param u
 * @param token
 */
	@Resource(name = "redisCache")
	private RedisCache redisCache;
	

	
	
	

	// topickeywords_banana
}