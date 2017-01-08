package com.sosxsos.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sosxsos.ssm.cache.RedisCache;
import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;
import com.sosxsos.ssm.service.CacheServiceRedis;
import com.sosxsos.ssm.util.CacheUtil;
import com.sosxsos.ssm.util.Const;

import net.sf.ehcache.Element;

@Service("cacheService")
public class CacheService implements CacheServiceRedis {
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
	@Override
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
	
	
	
	
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#updateCacheUse(com.sosxsos.ssm.entity.UserEntity, java.lang.String)
 */
@Override
public void updateCacheUse(UserEntity u,String token) {
	
		CacheUtil.updateCache(token, u, "userCacheEntity");

		CacheUtil.updateCache(u.getId(), u, "userCacheEntityByid");
	
}
	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#removeUserCache(java.lang.String, com.sosxsos.ssm.entity.UserEntity)
	 */
	@Override
	public void removeUserCache(String token,UserEntity u) {
		CacheUtil.removeCache(token, "userCache");
		CacheUtil.removeCache(token, "userCacheEntity");
		CacheUtil.removeCache(u.getId(), "userCacheEntityByid");
		
	}


	/* (non-Javadoc)
	 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getUserByTokenFromCache(java.lang.String)
	 */
	@Override
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
	@Override
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
	@Override
	public TransactionsEntity saveAndupdateTransaction(TransactionsEntity t) throws Exception{
		
		//dao.save("WebappuserMapper.saveTransactions", t);
		CacheUtil.cacheSave(t.getId(), t, "Transactions");
		return t;
	}
	
	
	
/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getTransactionFromCache(java.lang.String, java.lang.String)
 */
@Override
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
@Override
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
@Override
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
@Override
public void removeTransactionsFromCache(String id){
	CacheUtil.removeCache(id, "Transactions");
	CacheUtil.removeCache(id, "TransactionsLong");
}
	
	// TransactionsLong
	// UserBanana


/* (non-Javadoc)
 * @see com.sosxsos.ssm.service.impl.CacheServiceRedis#getBananaFromCache(java.lang.String)
 */
@Override
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
	@Override
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
	@Override
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
	@Override
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
	
	
	
	
	
	

	// topickeywords_banana
}