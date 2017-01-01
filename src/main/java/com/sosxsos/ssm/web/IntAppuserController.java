package com.sosxsos.ssm.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sosxsos.ssm.service.impl.AppuserService;
import com.sosxsos.ssm.service.impl.CacheService;
import com.sosxsos.ssm.service.impl.EmailService;
import com.sosxsos.ssm.service.impl.SmsService;

/**
 * 会员-接口类
 * 
 * 
 */
@Controller
@RequestMapping(value = "/appuser")
@SessionAttributes("test")
public class IntAppuserController {
	@Autowired
	private HttpServletRequest request;
	@Resource(name = "appuserService")
	private AppuserService appuserService;

	@Resource(name = "cacheService")
	private CacheService cacheService;

	@Resource(name = "emailService")
	private EmailService emailService;

	@Resource(name = "threadsPool")
	private ThreadPoolTaskExecutor threadsPool;

	@Resource(name = "smsService")
	private SmsService smsService;


}
