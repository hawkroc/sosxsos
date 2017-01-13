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
import com.sosxsos.ssm.dto.LoginRes;
import com.sosxsos.ssm.dto.ResBase;
import com.sosxsos.ssm.dto.ResCommon;
import com.sosxsos.ssm.dto.ResProfile;
import com.sosxsos.ssm.dto.Resident;
import com.sosxsos.ssm.dto.TheardingRes;
import com.sosxsos.ssm.dto.request.AddBananaRequest;
import com.sosxsos.ssm.dto.request.CommonRequst;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.LocationRangeEntity;
import com.sosxsos.ssm.entity.PushBean;
import com.sosxsos.ssm.entity.Threading;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;
import com.sosxsos.ssm.service.impl.AppuserService;
import com.sosxsos.ssm.service.impl.CacheService;
import com.sosxsos.ssm.service.impl.EmailService;
import com.sosxsos.ssm.service.impl.SmsService;
//import com.sosxsos.ssm.entity.LoginEntity;
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
public class IntAppuserController extends BaseController {
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
