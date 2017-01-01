package com.sosxsos.ssm.web;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fh.controller.app.request.AddBananaAction;
//import com.fh.controller.app.request.CommonRequst;
//import com.fh.controller.app.response.AddBananaRes;
//import com.fh.controller.app.response.CheckThoughtRes;
//import com.fh.controller.app.response.LoginResponse;
//import com.fh.controller.app.response.ResBase;
//import com.fh.controller.app.response.ResCommon;
//import com.fh.controller.app.response.ResProfile;
//import com.fh.controller.app.response.Resident;
//import com.fh.controller.app.response.SignUpResponse;
//import com.fh.controller.app.response.TheardingRes;
//import com.fh.controller.base.BaseController;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.LocationEntity;
import com.sosxsos.ssm.entity.LoginEntity;
import com.sosxsos.ssm.entity.PushBean;
import com.sosxsos.ssm.entity.SignUpEntity;
import com.sosxsos.ssm.entity.Threading;
import com.sosxsos.ssm.entity.TransactionsBeans;
import com.sosxsos.ssm.entity.UserEntity;
import com.sosxsos.ssm.service.impl.AppuserService;
import com.sosxsos.ssm.service.impl.CacheService;
import com.sosxsos.ssm.service.impl.EmailService;
import com.sosxsos.ssm.service.impl.SmsService;
import com.sosxsos.ssm.util.CacheUtil;
import com.sosxsos.ssm.util.Const;
import com.sosxsos.ssm.util.DateUtil;
import com.sosxsos.ssm.util.FileUtil;
import com.sosxsos.ssm.util.PageData;
import com.sosxsos.ssm.util.Tools;

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
