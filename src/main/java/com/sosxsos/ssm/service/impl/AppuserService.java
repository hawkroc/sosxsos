package com.sosxsos.ssm.service.impl;

import javax.annotation.Resource;

//import org.apache.ibatis.javassist.bytecode.annotation.IntegerMemberValue;
import org.springframework.stereotype.Service;

import com.sosxsos.ssm.dao.DaoSupport;

@Service("appuserService")
public class AppuserService {
	// private static Cache cache =
	// CacheManager.getInstance().getCache("myCache");
	@Resource(name = "pushNotificationService")
	private PushNotificationService ps;

	@Resource(name = "cacheService")
	private CacheService cacheService;

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	//@Transactional

	
}
