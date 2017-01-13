package com.sosxsos.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sosxsos.ssm.cache.RedisCache;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.BubbleEntity;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;
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
	
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#checkCodeByFrontEnd(int)
	 */

	public boolean checkCodeByFrontEnd(int code){
		Element o = CacheUtil.getCacheObject(code, "common");
	//	UserEntity user = null;
		
		boolean rs=false;
		if (o == null) {
			
			return rs;
		}
		//System.out.println("this is value in cache"+o.getObjectValue());
		long codetime = (long) o.getObjectValue();
		long sec = ((System.currentTimeMillis()) - codetime) / 1000;
		if(sec<Const.secEx){
		    rs=true;
		}
		CacheUtil.removeCache(code, "common");
		return rs;
		
		
	}
	
	public  void saveUserCache(UserEntity use,String token,String phone){
		CacheUtil.cacheSave(token, use, "userCacheEntity");
		CacheUtil.cacheSave(token, phone, "userCache");
			CacheUtil.cacheSave(use.getId(), use, "userCacheEntityByid");
	}
	
	
	
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#updateCacheUse(com.sosxsos.ssm.entity.UserEntity, java.lang.String)
 */

public void updateCacheUse(UserEntity u,String token) {
	
		CacheUtil.updateCache(token, u, "userCacheEntity");

		CacheUtil.updateCache(u.getId(), u, "userCacheEntityByid");
	
}
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#removeUserCache(java.lang.String, com.sosxsos.ssm.entity.UserEntity)
	 */

	public void removeUserCache(String token,UserEntity u) {
		CacheUtil.removeCache(token, "userCache");
		CacheUtil.removeCache(token, "userCacheEntity");
		CacheUtil.removeCache(u.getId(), "userCacheEntityByid");
		
	}


	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getUserByTokenFromCache(java.lang.String)
	 */

	public UserEntity getUserByTokenFromCache(String token) {
		Element o = CacheUtil.getCacheObject(token, "userCacheEntity");
		UserEntity user = null;
		if (o != null) {
			user = (UserEntity) o.getObjectValue();
		}
		return user;
	}
	

	
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getUserByTokenFromCache(int)
	 */

	public UserEntity getUserByTokenFromCache(int userid) {
		Element o = CacheUtil.getCacheObject(userid, "userCacheEntityByid");
		UserEntity user = null;
		if (o != null) {
			user = (UserEntity) o.getObjectValue();
		}
		return user;
	}
	
	
	
	// Transactions
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#saveAndupdateTransaction(com.sosxsos.ssm.entity.TransactionsEntity)
	 */
	public TransactionsEntity saveTempTransaction(TransactionsEntity t) {
		
		//dao.save("WebappuserMapper.saveTransactions", t);
		CacheUtil.cacheSave(t.getId(), t, "Transactions");
		return t;
	}
	
	
	
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getTransactionFromCache(java.lang.String, java.lang.String)
 */

public TransactionsEntity getTransactionFromCache(String id,String Cachename) throws Exception{
		
		//dao.save("WebappuserMapper.saveTransactions", t);
	Element o = CacheUtil.getCacheObject(id, Cachename);
	TransactionsEntity transactionsBeans=null;
	if(o!=null){
		transactionsBeans=(TransactionsEntity)o.getObjectValue();
	}
		return transactionsBeans;
	}
	
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getTransactionFromCacheById(java.lang.String)
 */

public TransactionsEntity getTransactionFromCacheById(String id) throws Exception{
	TransactionsEntity transactionsBeans=this.getTransactionFromCache(id, "Transactions");
	if(transactionsBeans==null){
		 transactionsBeans=this.getTransactionFromCache(id, "TransactionsLong");	
	}
     
	return transactionsBeans;
}
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#checkTimeout(java.lang.String)
 */

public int checkTimeout(String transactionId) throws Exception{
	int res=0;
	TransactionsEntity tmp=this.getTransactionFromCacheById(transactionId);
	if(tmp==null){
		res=Const.zoningtimeout;
		return res;
	}
	if(this.getBananaFromCacheById(tmp.getBanana_id())==null){
		res=Const.bananaExpired;
	}
	
	return res;
	
}
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#removeTransactionsFromCache(java.lang.String)
 */

public void removeTransactionsFromCache(String id){
	CacheUtil.removeCache(id, "Transactions");
	CacheUtil.removeCache(id, "TransactionsLong");
}
	
	// TransactionsLong
	// UserBanana


/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getBananaFromCache(java.lang.String)
 */

public BananaEntity getBananaFromCache(String phone) {
	Element o = CacheUtil.getCacheObject(phone, "UserBanana");
	BananaEntity rs = null;
	if (o != null) {
		rs = (BananaEntity) o.getObjectValue();
	}
	return rs;
}
	// UserBananaId
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getBananaFromCacheById(long)
	 */

	public BananaEntity getBananaFromCacheById(long banana_id) {
		Element o = CacheUtil.getCacheObject(banana_id, "UserBananaId");
		String phone = null;

		BananaEntity rsult = null;
		if (o != null) {
			phone = (String) o.getObjectValue();
			Element rs = CacheUtil.getCacheObject(phone, "UserBanana");
			rsult = (BananaEntity) rs.getObjectValue();
		}

		return rsult;
	}
	
	
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#updateBananaFromCacheByid(long, int)
	 */

	public BananaEntity updateBananaFromCacheByid(long banana_id,int status){
		Element o = CacheUtil.getCacheObject(banana_id, "UserBananaId");
		String phone = null;

		BananaEntity rsult = null;
		if (o != null) {
			phone = (String) o.getObjectValue();
			Element rs = CacheUtil.getCacheObject(phone, "UserBanana");
			rsult = (BananaEntity) rs.getObjectValue();
			rsult.setStatus(status);
			CacheUtil.updateCache(phone, rsult, "UserBanana");
		}

		return rsult;
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#removeBananaFromCacheByid(long)
	 */

	public void removeBananaFromCacheByid(long banana_id){
		
		Element o = CacheUtil.getCacheObject(banana_id, "UserBananaId");
		String phone = null;

		
		if (o != null) {
			phone = (String) o.getObjectValue();
			//Element rs = CacheUtil.getCacheObject(phone, "UserBanana");
			CacheUtil.removeCache(phone, "UserBanana");
			
		}
		CacheUtil.removeCache(banana_id, "UserBananaId");
		
		
	}
	
	/**
	 * 
	 * @param banana
	 * @param phone
	 */
	public void saveBananaCache(BananaEntity banana,String phone) {
		CacheUtil.cacheSave(phone, banana, "UserBanana");
		CacheUtil.cacheSave(banana.getId(), phone, "UserBananaId");
		BubbleEntity t= banana.getBubble();
		CacheUtil.cacheSave(t.getTopic() + t.getKey_word(), phone, "topickeywords_banana");
	}
	
	public BananaEntity geBananaEntityByKeyword(String topic,String keyword) {
		
		BananaEntity bananaEntity=null;
		Element o = CacheUtil.getCacheObject(topic + keyword, "topickeywords_banana");
		if (o != null) {
			BananaEntity b = this.getBananaFromCache((String) o.getObjectValue());
			// rs.setImage_url(thoughtEntity.getVedio_url());
			// rs.setVideo_url(thoughtEntity.getImage_url());

			//rs.setVideo_url(b.getBubble().getVideo_url());
		}
       return bananaEntity;
		
	}
	
	/**
	 * 
	 * @param t
	 */
	public void saveAndupdateTransactions(TransactionsEntity t){
		CacheUtil.cacheSave(t.getId(), t, "TransactionsLong");
		CacheUtil.removeCache(t.getId(), "Transactions");
	}
	
	
	
	

	// topickeywords_banana
}