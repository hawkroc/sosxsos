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
import com.sosxsos.ssm.dto.request.AddBananaRequest;

import com.sosxsos.ssm.dto.LoginRes;
import com.sosxsos.ssm.dto.ResBase;
import com.sosxsos.ssm.dto.ResCommon;
import com.sosxsos.ssm.dto.ResProfile;
import com.sosxsos.ssm.dto.Resident;
import com.sosxsos.ssm.dto.TheardingRes;
import com.sosxsos.ssm.dto.request.CommonRequst;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.LocationRangeEntity;
//import com.sosxsos.ssm.entity.LoginEntity;
import com.sosxsos.ssm.entity.PushBean;
import com.sosxsos.ssm.entity.Threading;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;
import com.sosxsos.ssm.util.CacheUtil;
import com.sosxsos.ssm.util.Const;
import com.sosxsos.ssm.util.DateUtil;
import com.sosxsos.ssm.util.FileUtil;
import com.sosxsos.ssm.util.PageData;
import com.sosxsos.ssm.util.Tools;
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



	/**
	 * 1.Current version
	 * 
	 * @param p
	 * @return
	 */
	// @RequestMapping(value = "/version", method = RequestMethod.GET)
	@RequestMapping(value = { "/version", "/versiontest" }, method = RequestMethod.GET)
	@ResponseBody

	public Object getCurrentVersion(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return new ResBase() {
		};

	}

	/**
	 * 2.1 login
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody

	public Object login(@RequestBody CommonRequst p, HttpServletResponse response) {

		LoginRes t = null;

		try {
			if (appuserService.checkPhone(p.getPhone()) == null && StringUtils.isEmpty(p.getUser_token())) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return t;
			}
			t = appuserService.updateLoginAppUser(p);

			if (t != null) {

				HttpSession s = this.getRequest().getSession();
				s.setAttribute("LoginResponse", t);

			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return t;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			e.printStackTrace();
		}
		t.setUser_token("Bearer "+t.getUser_token());
		return t;

	}

	/**
	 * 2.2 logout
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody

	public Object logout(HttpServletResponse response) {
		LoginRes t = null;
		String token =this.getToken();
		if ((checkToken())) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return t;
		}
		try {
			appuserService.updateLogout(token);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return t;

	}

	/**
	 * //2.3 verification_code
	 * 
	 * @param p
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/verification_code" }, method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object verifyCodeByPhone(@RequestBody CommonRequst p, HttpServletResponse response) {

		LoginRes rs = null;
		HttpSession s = this.getRequest().getSession();
		// System.out.println("tst");
		// String token = request.getHeader("Bearer");
		// boolean istype = StringUtils.isEmpty(p.getType());
		UserEntity userEntity = null;
		int Verification_code = Tools.getRandomNum();
		// System.out.println("test12334");
		try {
			userEntity = appuserService.getUserEntityByPhone(p.getPhone());

			if (userEntity != null) {
				String verified_email = userEntity.getVerified_email();
				if ("0".equalsIgnoreCase(p.getType())) {
					response.setStatus(HttpServletResponse.SC_CONFLICT);
					return null;

				} else {
					if (verified_email != null) {
						emailService.setCode(Verification_code);
						emailService.setMaill(verified_email);
						threadsPool.execute(emailService);
						// s.setAttribute("Verification_code",
						// Verification_code);
						// s.setAttribute("Verification_code_time",
						// System.currentTimeMillis());
						CacheUtil.cacheSave(Verification_code, System.currentTimeMillis(), "common");

					} else {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						return null;
					}
				}

			}

			if ("0".equalsIgnoreCase(p.getType())) {

				rs = new LoginRes();
				String sp = "+" + p.getPhone();

				smsService.setContent(String.valueOf(Verification_code));
				smsService.setPhone(sp);
				threadsPool.execute(smsService);
				// smsService.sendMessage(sp,
				// String.valueOf(Verification_code));

				rs.setVerification_code(String.valueOf(Verification_code));
				// System.out.println("ttt");
				// s.setAttribute("Verification_code", Verification_code);
				//
				// s.setAttribute("Verification_code_time",
				// System.currentTimeMillis());

				CacheUtil.cacheSave(Verification_code, System.currentTimeMillis(), "common");
				// System.out.println("dsfdsfdf");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * //2.3 sign up
	 * 
	 * @param p
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/sign_up" }, method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object signUp(@RequestBody CommonRequst p, HttpServletResponse response) {

		LoginRes rs = new LoginRes();
		HttpSession s = this.getRequest().getSession();
		// String token = request.getHeader("Bearer");
		// boolean istype = StringUtils.isEmpty(p.getType());

		try {

			UserEntity userEntity = appuserService.getUserEntityByPhone(p.getPhone());

			if (userEntity != null) {
				// System.out.println(userEntity.getPhone());
				response.setStatus(HttpServletResponse.SC_CONFLICT);

			} else {

				// if( s.getAttribute("Verification_code_time")==null){
				// response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				// return rs;
				// }
				// long time=(long) s.getAttribute("Verification_code_time") ;

				// long sec = ((System.currentTimeMillis()) - time) / 1000;
				int temp = Integer.valueOf(p.getVerification_code());
				// System.out.println("************2 :"+temp);
				if (cacheService.checkCodeByFrontEnd(temp)) {
					rs.setUser_token(appuserService.saveAppUser(p));

					response.setStatus(HttpServletResponse.SC_CREATED);
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return rs;
				}
				// System.out.println("************3
				// :"+p.getVerification_code());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("************4 :"+rs.getUser_token());
		rs.setUser_token("Bearer "+rs.getUser_token());
		return rs;

	}

	/**
	 * //2.4 https://api.sosxsos.com/v1/forgot
	 * 
	 * @param p
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/forgot" }, method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object forgotPassWord(@RequestBody CommonRequst p, HttpServletResponse response) {

		LoginRes rs = new LoginRes();
	//	String token = this.getToken();
		//boolean istype = StringUtils.isEmpty(p.getType());
		try {
			
			

			UserEntity userEntity = appuserService.getUserEntityByPhone(p.getPhone());

			if (userEntity == null) {
				// System.out.println(userEntity.getPhone());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;

			} else {
				int temp = Integer.valueOf(p.getVerification_code());
				if (cacheService.checkCodeByFrontEnd(temp)) {
				//	rs.setUser_token(appuserService.saveAppUser(p));
					rs.setUser_token(appuserService.updateAppUserPassword(p));
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return rs;
				}
			
			
			
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * 2.5 Create cross reference https://api.sosxsos.com/v1/cross_references
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/cross_references", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })

	public void create_cross_reference(@RequestBody PushBean p, HttpServletResponse response) {
		// String token = request.getHeader("Bearer");
		if (checkToken()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			// return null;
		} else {
			try {

				String token = this.getToken();
				appuserService.updatePushTacken(token, p);

				// to be done
				// Saved push_token
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// return t;
		}

	}

	/**
	 * 2.6 Verify email address https://api.sosxsos.com/v1/verification/codes
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/verification/codes", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object get_verification_code(@RequestBody CommonRequst email, HttpServletResponse response) {
		// String token = request.getHeader("Bearer");
		String token =this.getToken();
		UserEntity userEntity = getUserFromCache(token);

		if (userEntity == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;

		}

	
		int code = Tools.getRandomNum();
		// String token = request.getHeader("Bearer");

		userEntity.setCode(code);
		String verified_email = email.getEmail();
		userEntity.setVerified_email(verified_email);

		emailService.setCode(code);
		emailService.setMaill(verified_email);
		threadsPool.execute(emailService);
		cacheService.updateCacheUse(userEntity, token);
		return null;

	}

	/**
	 * 2.6.1 Verify email address https://api.sosxsos.com/v1/verification/codes
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/verification/emails", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object verify_email(@RequestBody CommonRequst code, HttpServletResponse response) {
		String token = this.getToken();
       
		UserEntity userEntity = getUserFromCache(token);
	

		//System.out.println("this token is " + token);
		if (userEntity == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		if (StringUtils.isNotEmpty(code.getCode())) {
			int n = Integer.valueOf((code.getCode()));
			if (n != 0 || n == userEntity.getCode()) {
				// update status in db
				try {
					appuserService.updateUserMail(userEntity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setStatus(HttpServletResponse.SC_OK);

			} else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				// userEntity.setVerified_email(null);
			}
			// userEntity.setCode(0);
			cacheService.updateCacheUse(userEntity, token);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		return null;
	}

	// /**
	// * 2.6.1 Verify email address
	// https://api.sosxsos.com/v1/verification/codes
	// *
	// * @param p
	// * @return
	// */
	// @RequestMapping(value = "verification/emails", method =
	// RequestMethod.POST, produces = {
	// "application/json;charset=UTF-8" })
	// public void verify_email(CommonRequst code, HttpServletResponse response)
	// {
	// // String token = request.getHeader("Bearer");
	// if (checkToken()) {
	// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	//
	// } else {
	// // Verify mail
	//
	// }
	//
	// }

	/**
	 * 2.7 Upload ID photos
	 * https://api.sosxsos.com/v1/verification/identifications
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "verification/identifications", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object identifications(@RequestParam("photo_front") CommonsMultipartFile image_front,
			@RequestParam("photo_back") CommonsMultipartFile image_back,
			@RequestParam("photo_selfie") CommonsMultipartFile image_selfie, HttpServletResponse response) {
		// String token = request.getHeader("Bearer");
		UserEntity userEntity = getUserFromCache();
		if (userEntity == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;

		}

		
	//	System.out.println("photo_front  "+image_front.getOriginalFilename());
		//System.out.println("photo_back  "+image_back.getOriginalFilename());
	
		// System.out.println(vidoname);
//		if (!FileUtil.checkFileType(photo_front, Const.imageType)
//				|| !FileUtil.checkFileType(photo_back, Const.imageType)
//				|| !FileUtil.checkFileType(photo_selfie, Const.imageType)) {
//			 System.out.println("this is the img name is error ");
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}

		// long l = new Date().getTime();

		String imagename = userEntity.getPhone()+Const.imageType;

		String Imagepath_photo_front = Const.profile+"photo_front/" + imagename;
		
		String Imagepath_photo_back = Const.profile+"photo_back/" + imagename;
		String Imagepath_photo_selfie = Const.profile+"photo_selfie/" + imagename;
		
		
		

		// String i = "user_profile/" + imagename;

		//ObjectMapper mapper = new ObjectMapper();

		File front = new File(Imagepath_photo_front);
		File back = new File(Imagepath_photo_back);
		File selfie = new File(Imagepath_photo_selfie);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		try {
			image_front.transferTo(front);
			image_back.transferTo(back);
			image_selfie.transferTo(selfie);
		
			appuserService.updateUserProfile(userEntity);

		
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 3.2 Report current user location
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/current_locations", method = RequestMethod.PUT, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody

	public Object updateLocation(@RequestBody LocationRangeEntity p, HttpServletResponse response) {

		//String token = this.getToken();
		UserEntity userEntity= getUserFromCache();
		if (userEntity==null) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		} 
			
		p.setPhone(userEntity.getPhone());	
		try {

			appuserService.updateUserLocation(p);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
		}
		return null;

	}
    /**
     * 
     * @return
     */
	private String getToken(){
		String token = request.getHeader("Authorization");

	//
		String rs=null;
		if(token!=null){
		rs=token.replace("Bearer", "").trim();
		//System.out.println("234+"+rs);
		}
		return rs;
		
	}
	
	
	private boolean checkToken() {
		String token = this.getToken();
		//System.out.println("thisdfdsfdf token is " + token);
		boolean rs = false;
		if (StringUtils.isEmpty(token) || appuserService.getPhoneByTokenFromCache(token) == null) {
			rs = true;
		}

		return rs;
	}

	private UserEntity getUserFromCache(String token) {

		return cacheService.getUserByTokenFromCache(token);
	}

	private UserEntity getUserFromCache() {

		String token = this.getToken();
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		return this.getUserFromCache(token);
	}

	/**
	 * 4.2 @critical Add a banana
	 * 
	 * @param p
	 * @return MultipartHttpServletRequest request
	 */
	@RequestMapping(value = "/bananas", method = RequestMethod.POST)
	@ResponseBody

	public Object addBanana(@RequestParam("video") CommonsMultipartFile video,
			@RequestParam("image") CommonsMultipartFile image, @RequestParam("json") String json,
			HttpServletResponse response) {
		ResCommon t = null;
		String token =this.getToken();
		if (checkToken()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		String vidoname = video.getOriginalFilename();
		String imgname = image.getOriginalFilename();
		if (!FileUtil.checkFileType(vidoname, Const.vidoType) || !FileUtil.checkFileType(imgname, Const.imageType)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		long l = new Date().getTime();

		String videoname = l + vidoname;

		String imagename = l + imgname;

		String Imagepath = Const.Imagepath + imagename;
		String Videopath = Const.Videopath + videoname;


		String v = "video/" + videoname;
		String i = "image/" + imagename;

		ObjectMapper mapper = new ObjectMapper();
		File newVideo = new File(Videopath);
		File newImage = new File(Imagepath);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		try {
			video.transferTo(newVideo);
			image.transferTo(newImage);

			AddBananaRequest addBananaAction = mapper.readValue(json, AddBananaRequest.class);
			// System.out.println("test key word: " +
			// addBananaAction.getBanana().getBubble().getKey_word());
			t = appuserService.saveBanana(addBananaAction.getBanana(), token, i, v);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return t;

	}

	/**
	 * // 4.1 @high Check bubbles http://api.sosxsos.com/checkThought
	 * 
	 * @param p
	 * @return
	 */
	// @RequestMapping("/userGrid",
	// params = {"_search", "nd", "rows", "page", "sidx", "sort"})
	@RequestMapping(value = "/bubbles", params = { "topic", "key_word" }, method = RequestMethod.GET)
	@ResponseBody

	public Object checkBubbles(@RequestParam(value = "topic") String topic,
			@RequestParam(value = "key_word") String key_word, HttpServletResponse response) {

		if (checkToken()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		ResCommon t = null;
		try {
			t = appuserService.checkBubbles(topic.trim(), key_word.trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (t == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return t;

	}

	////////////////

	/**
	 * 3.1 @high Get nearby residents
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/residents", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody

	public Object residentList(@RequestParam(value = "latitude") Double latitude,
			@RequestParam(value = "longitude") Double longitude, @RequestParam(value = "accuracy") Double accuracy,
			HttpServletResponse response) {
        UserEntity userEntity=getUserFromCache();
		if (userEntity==null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		List<Resident> list = null;
		try {
			list = appuserService.getResidentList(latitude, longitude, accuracy,userEntity.getPhone());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	// 5.1 Start Transaction & Zoning

	/**
	 * //5.1.1 Start Transaction & Zoning
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public ResCommon startTransaction(@RequestBody CommonRequst common, HttpServletResponse response) {

		// 5.1.1 Start Transaction & Zoning
		//// 5.2 Threading https://api.sosxsos.com/v1/transactions/#/threading
		// 5.3 Finish the transaction
		// * //5.4 Cancel the transaction
		// *https://api.sosxsos.com/v1/transactions/#/cancellation
		ResCommon result = new ResCommon();
		String token = this.getToken();
		UserEntity getby = getUserFromCache(token);

		UserEntity shareby = null;
		TransactionsEntity t = null;

		if (getby == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return result;

		}

		if (getby.getStatus() < 2) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			result.setResponseResult(HttpServletResponse.SC_FORBIDDEN);
			return result;
		}

		if (common != null && common.getBanana_id() != 0) {
			BananaEntity banana = cacheService.getBananaFromCacheById(common.getBanana_id());
			if (banana == null) {
				response.setStatus(HttpServletResponse.SC_GONE);
				return result;
			}
			System.out.println(getby.getId() + 1);
			if (banana.getStatus() == 1) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return result;
			}
			shareby = cacheService.getUserByTokenFromCache(banana.getUserid());

			if (shareby.getStatus() < 2) {
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
				return result;
			}

			try {
				t = appuserService.generateTransactionsBeans(getby, banana.getId(), shareby);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (t != null) {

			result.setTransaction_id(t.getId());
			result.setStatus(t.getStatus());

			// Zoning push notification
		}
		return result;

	}

	/**
	 * 
	 * @param id
	 * @param common
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/transactions/{id}/zoning" }, method = RequestMethod.POST)
	@ResponseBody

	public ResCommon zoningActive(@PathVariable String id, @RequestBody CommonRequst common,
			HttpServletResponse response) {
		System.out.println("this zoningActive is ");
		ResCommon rs = new ResCommon();
		int status = 0;
		// System.out.println(test);
		if (checkToken()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}
		if (common == null || !StringUtils.isNotEmpty(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return rs;
		}

		TransactionsEntity transactionsBeans = null;
		try {
			transactionsBeans = cacheService.getTransactionFromCache(id, "Transactions");
			if (transactionsBeans == null) {
				status = Const.zoningtimeout;
				// push notifiction timeout
				response.setStatus(HttpServletResponse.SC_GONE);
				return rs;

			}

			rs.setTransaction_id(transactionsBeans.getId());
			BananaEntity banana = cacheService.getBananaFromCacheById(transactionsBeans.getBanana_id());
			if (banana == null) {
				// push notifiction timeout
				status = Const.bananaExpired;
				response.setStatus(HttpServletResponse.SC_GONE);
				return rs;
			}

			status = transactionsBeans.getStatus();

			if (common.isZone()) {
				status = Const.zoned;
				transactionsBeans.setZoned_time(DateUtil.getTimeBySecondChange(7200));
				appuserService.updateZoningTransaction(transactionsBeans, Const.zoned);
				cacheService.updateBananaFromCacheByid(transactionsBeans.getBanana_id(), 1);
				// push zooed message success

			} else {
				// push zooing message faild zoning ignored by Sharesby
				status = Const.Ignored;
				cacheService.removeTransactionsFromCache(id);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.setStatus(status);

		return rs;

	}

	/**
	 * 
	 * @param id
	 * @param common
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/transactions/{id}/threading" }, method = RequestMethod.POST)
	@ResponseBody

	public ResCommon threadingActive(@PathVariable String id, @RequestBody CommonRequst common,
			HttpServletResponse response) {
		// System.out.println("this threadingActive is ");
		ResCommon rs = null;
		UserEntity user = getUserFromCache();
		boolean isGetBy = true;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}
		if (common == null || !StringUtils.isNotEmpty(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return rs;
		}

		TransactionsEntity transactionsBeans = null;
		try {
			transactionsBeans = cacheService.getTransactionFromCache(id, "TransactionsLong");

			if (transactionsBeans == null) {
				response.setStatus(HttpServletResponse.SC_GONE);
				return rs;

			}

			int status = transactionsBeans.getStatus();

			if (transactionsBeans.getSharesby() == user.getId()) {
				isGetBy = false;
			}

			Threading threading = transactionsBeans.getThreading();

			if (threading == null) {
				threading = new Threading();

			}

			if (common.isAgree()) {
				// threading
				status = Const.threaded;
				appuserService.updateCommonTransaction(transactionsBeans, status, null);

				// push message threading success

			} else {
				int n = 0;
				if (isGetBy) {
					n = threading.getGetByTimes() + 1;
					if (n > 2) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						status = Const.Threading_limit;
						// push message
						return null;
					} else {
						threading.setGetByTimes(n);
					}

				} else {
					n = threading.getShareByTimes() + 1;

					if (n > 1) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						status = Const.Threading_limit;
						// push message
						return null;
					} else {
						threading.setShareByTimes(n);
					}

				}
				threading.setPlace(common.getPlace());
				threading.setTime(common.getTime());
				status = Const.Threading_received;
				appuserService.updateCommonTransaction(transactionsBeans, status, threading);
				// push message thread request be received

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	@RequestMapping(value = { "/transactions/{id}/cancellation" }, method = RequestMethod.POST)
	@ResponseBody

	public ResCommon cancellActive(@PathVariable String id, @RequestBody CommonRequst common,
			HttpServletResponse response) {
		System.out.println("this cancellActive is ");
		ResCommon rs = null;
		UserEntity user = getUserFromCache();
		boolean isGetBy = true;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}
		if (common == null || !StringUtils.isNotEmpty(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return rs;
		}

		TransactionsEntity transactionsBeans = null;
		try {
			transactionsBeans = cacheService.getTransactionFromCache(id, "TransactionsLong");

			if (transactionsBeans == null) {
				response.setStatus(HttpServletResponse.SC_GONE);
				return rs;

			}

			if (transactionsBeans.getSharesby() == user.getId()) {
				isGetBy = false;
			}
			int cancle_reason = common.getCancel_reason();
			int status = transactionsBeans.getStatus();
			if (cancle_reason != 0) {
				if (transactionsBeans.getCancle_reason() != 0) {

					response.setStatus(HttpServletResponse.SC_CONFLICT);
					return rs;
				}
				transactionsBeans.setCancle_reason(cancle_reason);
				// transactionsBeans.setStatus(Const.Cancel_received);
				status = Const.Cancel_received;
				// appuserService.updateCommonTransaction(transactionsBeans,
				// Const.Cancel_received, null);

			} else {

				if (common.isAgree() && transactionsBeans.getCancle_reason() != 0) {
					status = Const.Closed;
					// appuserService.updateCommonTransaction(transactionsBeans,
					// Const.Closed, null);
				} else {
					// appuserService.updateCommonTransaction(transactionsBeans,
					// , null);
					status = Const.Dealbreaker;
				}

				cacheService.updateBananaFromCacheByid(transactionsBeans.getBanana_id(), 0);

			}
			appuserService.updateCommonTransaction(transactionsBeans, status, null);

			// common.getCancel_reason()

			///

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * 
	 * @param id
	 * @param common
	 * @param response
	 * @return
	 */

	@RequestMapping(value = { "/transactions/{id}/finish" }, method = RequestMethod.POST)
	@ResponseBody

	public ResCommon finishedActive(@PathVariable String id, @RequestBody CommonRequst common,
			HttpServletResponse response) {
		// System.out.println("this finishedActive is ");
		ResCommon rs = null;
		UserEntity user = getUserFromCache();
		boolean isGetBy = true;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}
		if (common == null || !StringUtils.isNotEmpty(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return rs;
		}

		TransactionsEntity transactionsBeans = null;
		try {
			transactionsBeans = cacheService.getTransactionFromCache(id, "TransactionsLong");

			if (transactionsBeans == null) {
				response.setStatus(HttpServletResponse.SC_GONE);
				return rs;

			}
			int status = transactionsBeans.getStatus();
			if (status == Const.Cancel_received) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return rs;
			}

			if (status == Const.Finshed_receive) {
				status = Const.Finshed;
				cacheService.removeBananaFromCacheByid((transactionsBeans.getBanana_id()));
			} else {
				status = Const.Finshed_receive;
			}

			if (transactionsBeans.getSharesby() == user.getId()) {
				isGetBy = false;
			}

			appuserService.updateCommonTransaction(transactionsBeans, status, null);

			// push message thread request be received

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * 7.1 Get transaction
	 * 
	 * @param p
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = { "/transactions" }, method = RequestMethod.GET)
	@ResponseBody

	public List<PageData> queryTransaction(HttpServletResponse response) {

		List<PageData> rs = null;
		// System.out.println(test);
		UserEntity user = getUserFromCache();
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}

		try {
			rs = appuserService.queryTransactionsListShareBy(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * 7.2 Get transaction details
	 * 
	 * @param p
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = { "/transactions/{id}" }, method = RequestMethod.GET)
	@ResponseBody

	public TransactionsEntity queryTransactionDetail(@PathVariable String id, HttpServletResponse response) {
		// System.out.println("this dsfsdf is " + id);
		TransactionsEntity rs = null;
		// System.out.println(test);
		UserEntity user = getUserFromCache();
		TransactionsEntity res = null;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return res;
		}

		try {
			res = appuserService.queryTransactionsDetail(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	// Get active threadings

	// https://api.sosxsos.com/v1/threadings
	// 8.1 Get active threadings
	@RequestMapping(value = { "/threadings" }, method = RequestMethod.GET)
	@ResponseBody

	public TheardingRes getThreadings(HttpServletResponse response) {
		// System.out.println(test);
		UserEntity user = getUserFromCache();
		TheardingRes rs = null;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return rs;
		}

		try {
			rs = appuserService.queryTheardingsByUserID(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	// Get active zoning requests

	// https://api.sosxsos.com/v1/zoning_requests
	// 8.2 Get active zoning requests
	@RequestMapping(value = { "/zoning_requests" }, method = RequestMethod.GET)
	@ResponseBody

	public Object getZoning_requests(HttpServletResponse response) {

		UserEntity user = getUserFromCache();
		// TheardingRes rs=null;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		return new ResBase() {
		};

	}

	// 9.1 Get user profile

	// https://api.sosxsos.com/v1/residents/#
	@RequestMapping(value = { "/residents//profile/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public ResProfile getResidents(HttpServletResponse response) {

		UserEntity user = getUserFromCache();
		// TheardingRes rs=null;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		BananaEntity bananaEntity = cacheService.getBananaFromCache(user.getPhone());
		ResProfile rs = new ResProfile();
		rs.setStatus(user.getStatus());
		rs.setVerified_email(user.getVerified_email());
		rs.setVerified_id(user.isVerified_id());
		rs.setStatistics(user.getStatisticsEntity());
		if (bananaEntity != null) {
			rs.setBubble(bananaEntity.getBubble());

			rs.setMedia(bananaEntity.getMedia());

		}

		return rs;

	}

	// 9.2 Report abuse https://api.sosxsos.com/v1/reports
	// {
	// "type": int,
	// }
	@RequestMapping(value = { "/reports" }, method = RequestMethod.POST)
	@ResponseBody

	public Object makeReportsAbuse(HttpServletResponse response) {

		UserEntity user = getUserFromCache();
		// TheardingRes rs=null;
		// System.out.println(test);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		// String token = request.getHeader("Bearer");
		// user.setReported(user.getReported()+1);
		// cacheService.updateCacheUse(user, token);

		return new ResBase() {
		};

	}

}

