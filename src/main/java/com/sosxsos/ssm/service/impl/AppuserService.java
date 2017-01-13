package com.sosxsos.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.javassist.bytecode.annotation.IntegerMemberValue;
import org.springframework.stereotype.Service;

import com.sosxsos.ssm.dao.DaoSupport;
import com.sosxsos.ssm.dto.LoginRes;
import com.sosxsos.ssm.dto.ResCommon;
import com.sosxsos.ssm.dto.Resident;
import com.sosxsos.ssm.dto.TheardingRes;
import com.sosxsos.ssm.dto.request.CommonRequst;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.BubbleEntity;
import com.sosxsos.ssm.entity.LocationRangeEntity;
//import com.sosxsos.ssm.entity.LoginEntity;
//import com.sosxsos.ssm.entity.LoginEntity;
import com.sosxsos.ssm.entity.MediaEntity;
import com.sosxsos.ssm.entity.ProductEntity;
import com.sosxsos.ssm.entity.PushBean;
//import com.sosxsos.ssm.entity.SignUpEntity;
import com.sosxsos.ssm.entity.StatisticsEntity;
import com.sosxsos.ssm.entity.Threading;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;
//import com.sosxsos.ssm.util.CacheUtil;
import com.sosxsos.ssm.util.Const;
import com.sosxsos.ssm.util.DateUtil;
import com.sosxsos.ssm.util.LatLonUtil;
import com.sosxsos.ssm.util.MD5;
import com.sosxsos.ssm.util.PageData;

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

	
}
