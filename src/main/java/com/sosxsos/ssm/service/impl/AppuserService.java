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

import net.sf.ehcache.Element;

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
	// ======================================================================================

	/*
	 * 通过id获取数据
	 */
	public PageData findByUiId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findByUiId", pd);
	}

	/*
	 * 通过loginname获取数据
	 */

	public PageData findByUId(PageData pd) throws Exception {

		return (PageData) dao.findForObject("AppuserMapper.findByUId", pd);
	}

	/**
	 * 
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public Integer checkPhone(String phone) throws Exception {

		java.lang.Integer res = (Integer) dao.findForObject("WebappuserMapper.checkUser", phone);
		return res;

	}
	
	
	
	public UserEntity getUserEntityByPhone(String phone) throws Exception {
		System.out.println("this phone is "+phone);
		UserEntity res= (UserEntity) dao.findForObject("WebappuserMapper.checkUserEntity", phone);
		return res;
	}
	

	/**
	 * 
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public void dropDatabase(String database) throws Exception {
		dao.delete("WebappuserMapper.reward", database);
		// return res;

	}

	/**
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public LoginRes updateLoginAppUser(CommonRequst e) throws Exception {
		LoginRes loginResponse = null;

		if (StringUtils.isNotEmpty(e.getPassword()) && StringUtils.isNotEmpty(e.getPhone())) {
			Object temp = dao.findForObject("WebappuserMapper.login", e);
			if (temp == null) {
				return loginResponse;
			}
			Integer id = (int) temp;

			if (id != null) {
				loginResponse = new LoginRes();
				// dao.update("WebappuserMapper.saveLocation", e);
				loginResponse.setUser_token(getUserTokenAndPutCache(e.getPhone()));
			}

		} else if (StringUtils.isNotEmpty(e.getUser_token())) {
			// String phone = getPhoneByTokenFromCache(e.getUser_token());
			UserEntity user = cacheService.getUserByTokenFromCache(e.getUser_token());
			if (user != null) {
				String phone = user.getPhone();
				e.setPhone(phone);
				loginResponse = new LoginRes();
				loginResponse = (LoginRes) dao.findForObject("WebappuserMapper.loginByToken", phone);
				// dao.update("WebappuserMapper.saveLocation", e);
			}

		}

		return loginResponse;

	}

	public void updateUserLocation(LocationRangeEntity e) throws Exception {

		dao.update("WebappuserMapper.saveLocation", e);
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getPhoneByTokenFromCache(String token) {
		Element o = CacheUtil.getCacheObject(token, "userCache");
		String phone = null;
		if (o != null) {
			phone = (String) o.getObjectValue();
		}
		return phone;
	}

	/**
	 * 
	 * @param user
	 * @param banana_id
	 * @return
	 * @throws Exception
	 */
	public TransactionsEntity generateTransactionsBeans(UserEntity getby, long banana_id,UserEntity shareby) throws Exception {

		long sharesby =shareby.getId();// this.getUserIdByBananaId(banana_id);
		long getsby = getby.getId();
		TransactionsEntity transactionsBeans = new TransactionsEntity();
		transactionsBeans.setId(MD5.md5(System.currentTimeMillis() + banana_id));
		transactionsBeans.setStatus(Const.zoning);
		transactionsBeans.setBanana_id(banana_id);
		transactionsBeans.setGetsby(getsby);
		transactionsBeans.setSharesby(sharesby);
		cacheService.saveAndupdateTransaction(transactionsBeans);
		return transactionsBeans;
		// saveAndupdateTransaction

	}

	private long getUserIdByBananaId(long banana_id) throws Exception {

		BananaEntity bananaEntity = cacheService.getBananaFromCacheById(banana_id);
		long sharesby;
		if (bananaEntity != null) {
			return sharesby = bananaEntity.getUserid();
		} else {
			return sharesby = (long) dao.findForObject("WebappuserMapper.queryUserByBanana", banana_id);
		}

		// return sharesby;
	}

	/**
	 * only for zoning
	 * 
	 * @param transactions_id
	 * @param status
	 * @throws Exception
	 */
	public TransactionsEntity updateZoningTransaction(TransactionsEntity transactions, int status) throws Exception  {
		TransactionsEntity transactionsBeans =transactions;

		if (transactionsBeans != null) {
			transactionsBeans.setPrev_status(transactionsBeans.getStatus());
			transactionsBeans.setStatus(status);
			this.saveAndupdateTransaction(transactionsBeans);
		}

		return transactionsBeans;
	}
	
	


	//
	//
	// 10 - zoning, waiting for Sharesby to respond
	// 11 - zoned

	// 20 - threading request sent, this user is waiting for the other party to
	//      respond 
	// 21 - threading request received, this user needs to answer

	// 23 - threaded
	
	
	// 30 - the current user clicked "finish", waiting for the other party to
	//      respond
	// 31 - the current user clicked "cancel", waiting for the other party to
	//      respond
	// 32 - the other party clicked "cancel", the current user needs to "agree"
	//      or "disagree"
	
	// 40 - zoning ignored by Sharesby	
	// 41 - zoning 2mins timeout
	
	
	// 42 - banana expired before zoned successfully
	// 43 - threading negotiation times reached limit
	
	// 90 - finished successfully
	// 91 - dealbreaker
	// 92 - closed (either: 1. someone canceled and the other party also agrees
	//      2. transaction timeout)

	//public TransactionsBeans updateStatusTransaction(String transactions_id)

	/**
	 * 
	 * @param transactions_id
	 * @param status
	 * @param t
	 * @throws Exception
	 */
	public TransactionsEntity updateCommonTransaction(TransactionsEntity transactions, int status, Threading t)
			throws Exception {
		// TransactionsBeans transactionsBeans=
		// cacheService.getTransactionFromCache(transactions_id,"TransactionsLong");
		transactions.setPrev_status(transactions.getStatus());
		if (t != null) {
			transactions.setThreading(t);
		}

		CacheUtil.updateCache(transactions.getId(), transactions, "TransactionsLong");
		dao.update("WebappuserMapper.updateTransactions", transactions);

		return transactions;

	}

	/**
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	private TransactionsEntity saveAndupdateTransaction(TransactionsEntity t) throws Exception  {
		//t.setStatus(status);
		dao.save("WebappuserMapper.saveTransactions", t);
	//	System.out.println(t.getId());
//		CacheUtil.cacheSave(t.getId(), t, "TransactionsLong");
//		CacheUtil.removeCache(t.getId(), "Transactions");
		cacheService.saveAndupdateTransaction(t);
		return t;

	}
	
	
	public List<PageData> queryTransactionsListShareBy(UserEntity u) throws Exception{
		//dao.save("WebappuserMapper.saveTransactions", t);
		return (List<PageData>)	dao.findForList("WebappuserMapper.queryTransactionsListShareBy", u.getId());
	
	}
	
	public List<PageData> queryTransactionsListGetBy(UserEntity u) throws Exception{
		//dao.save("WebappuserMapper.saveTransactions", t);
		return (List<PageData>)	dao.findForList("WebappuserMapper.queryTransactionsListGetBy", u.getId());
	
	}
	
	public TransactionsEntity queryTransactionsDetail(String id) throws Exception{
		return (TransactionsEntity)	dao.findForObject("WebappuserMapper.queryTransactionsDetail", id);
	}
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public TheardingRes queryTheardingsByUserID(UserEntity user) throws Exception{
		TheardingRes theardingRes= new TheardingRes();
		theardingRes.setGetsby_threadings(this.queryTransactionsListGetBy(user));
		theardingRes.setSharesby_threadings(this.queryTransactionsListShareBy(user));
		return theardingRes;
	}

	/**
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	private String getUserTokenAndPutCache(String phone) throws Exception {
		String token =MD5.md5((phone + System.currentTimeMillis()));
		
		UserEntity use = (UserEntity) dao.findForObject("WebappuserMapper.queryUser", phone);
		
	if(use==null){
		System.out.println("this is null");
	}
//	CacheUtil.cacheSave(token, use, "userCacheEntity");
//	CacheUtil.cacheSave(token, phone, "userCache");
//		CacheUtil.cacheSave(use.getId(), use, "userCacheEntityByid");
	cacheService.saveUserCache(use, token, phone);
		return token;

	}

	/**
	 * 
	 * @param token
	 * @param push
	 * @throws Exception
	 */
	public void updatePushTacken(String token, PushBean push) throws Exception {
		UserEntity userEntity = cacheService.getUserByTokenFromCache(token);
		userEntity.setPush_type(push.getPush_system());
		userEntity.setPush_token(push.getPush_token());
		cacheService.updateCacheUse(userEntity, token);
		PageData pageData = new PageData();
		pageData.put("type", userEntity.getPush_type());
		pageData.put("token", userEntity.getPush_token());
		pageData.put("phone", userEntity.getPhone());
		dao.update("WebappuserMapper.savePushToken", pageData);

	}
	
	public void updateUserMail(UserEntity u) throws Exception{
	//	cacheService.updateCacheUse(u, token);
		PageData pageData = new PageData();
        pageData.put("mail", u.getVerified_email());
		pageData.put("phone", u.getPhone());
		dao.update("WebappuserMapper.saveMail", pageData);
		
	}
	
	
	public void updateUserProfile(UserEntity u) throws Exception{
	//	cacheService.updateCacheUse(u, token);
		PageData pageData = new PageData();
       // pageData.put("mail", u.getVerified_email());
		pageData.put("phone", u.getPhone());
		dao.update("WebappuserMapper.updateProfile", pageData);
		
	}
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public List<Resident> getResidentList(double latitude, double longitude, double accuracy,String phone) throws Exception {

		List<Resident> residents = null;
		System.out.println("latitude  "+latitude+"  longitude  "+longitude);

		LocationRangeEntity l = LatLonUtil.getInstance().getDefaultAround(latitude, longitude);
		l.setCurrent_time(DateUtil.getTime());
		l.setPhone(phone);
		System.out.println(l.getCurrent_time());
		residents = (List<Resident>) dao.findForList("WebappuserMapper.searchResident", l);
		if (residents != null) {
			for (Resident resident : residents) {
				System.out.println(resident.getPhone()+resident.getUser_id());
				resident.setBanana(cacheService.getBananaFromCache(resident.getPhone()));
			}
		}

		return residents;

	}

	// need to be done
	public ResCommon checkBubbles(String topic, String keyword) throws Exception {

		ResCommon rs = null;

		// r.getThought_idthougth();
		rs = new ResCommon();
	
       BananaEntity bananaEntity=  cacheService.geBananaEntityByKeyword(topic,keyword);
       if(bananaEntity!=null){
    	   rs.setVideo_url(bananaEntity.getBubble().getVideo_url());
       }
		

		return rs;

	}

	// add throuts

	public ResCommon saveBanana(BananaEntity banana, String token, String Imagepath, String Videopath)
			throws Exception {

		// List<Resident> residents = null;
		// if (getPhoneByTokenFromCache(r.getUser_token()) != null) {
		// LocationRangeEntity l =
		// LatLonUtil.getInstance().getDefaultAround(r.getGeo_lat(),
		// r.getGet_lng());
		// residents = (List<Resident>)
		// dao.findForObject("WebappuserMapper.searchResident", l);
		// }
		ResCommon residents = new ResCommon();
		// String phone = getPhoneByTokenFromCache(token);
		// //for test
		// // phone="334455";
		// //phone="123456";
		//
		// if (phone == null) {
		// return residents;
		// }.
		UserEntity userEntity = cacheService.getUserByTokenFromCache(token);
		int userid = userEntity.getId();
		String phone = userEntity.getPhone();

		banana.setUserid(userid);
		//System.out.println(banana.toString());
		BubbleEntity t = banana.getBubble();

		t.setUserid(userid);
		t.setImage_url(Imagepath);
		t.setVideo_url(Videopath);
		//System.out.println(t.toString());
		ProductEntity product = banana.getProduct();
		//System.out.println(product.toString());
		product.setName(product.getItem_info().getName());
		product.setPrice(product.getItem_info().getPrice());
		dao.save("WebappuserMapper.saveThought", t);
		dao.save("WebappuserMapper.saveProduct", product);
		banana.setProductId(product.getId());
		banana.setThoughtId(t.getId());

		dao.save("WebappuserMapper.saveBanana", banana);
		MediaEntity mediaEntity = new MediaEntity();
		mediaEntity.setImage_url(Imagepath);
		mediaEntity.setVideo_url(Videopath);
		banana.setMedia((mediaEntity));

		
//		CacheUtil.cacheSave(phone, banana, "UserBanana");
//		CacheUtil.cacheSave(banana.getId(), phone, "UserBananaId");
//		CacheUtil.cacheSave(t.getTopic() + t.getKey_word(), phone, "topickeywords_banana");
		cacheService.saveBananaCache(banana,phone);
		// residents.setStatus(0);
		// residents.setThought_id(t.getId());
		residents.setBanana_id(banana.getId());
		residents.setVideo_url(Videopath);
		residents.setImage_url(Imagepath);
		return residents;

	}

	/**
	 * 
	 * @param e
	 */
	public void updateLogout(String token) throws Exception {
		UserEntity userEntity = cacheService.getUserByTokenFromCache(token);
		dao.update("WebappuserMapper.logout", userEntity.getPhone());
		cacheService.removeUserCache(token, userEntity);
	}

	/*
	 * 保存webapp用户
	 */
	public String saveAppUser(CommonRequst p) throws Exception {
		
		dao.save("WebappuserMapper.saveU", p);
		StatisticsEntity statisticsEntity= new StatisticsEntity();
		statisticsEntity.setUser_id(p.getId());
		dao.save("WebappuserMapper.saveStatistics", p.getId());
		return this.getUserTokenAndPutCache(p.getPhone());
	}

	public String updateAppUserPassword(CommonRequst p) throws Exception {
		dao.save("WebappuserMapper.updatePassword", p);
		return this.getUserTokenAndPutCache(p.getPhone());
	}

	
}
