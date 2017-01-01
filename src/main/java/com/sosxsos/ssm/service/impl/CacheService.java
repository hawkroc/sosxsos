package com.sosxsos.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.TransactionsBeans;
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
			return true;
		}
		CacheUtil.removeCache(code, "common");
		return rs;
		
		
	}
	
	
	
	
public void updateCacheUse(UserEntity u,String token) {
	
		CacheUtil.updateCache(token, u, "userCacheEntity");

		CacheUtil.updateCache(u.getId(), u, "userCacheEntityByid");
	
}
	public void removeUserCache(String token,UserEntity u) {
		CacheUtil.removeCache(token, "userCache");
		CacheUtil.removeCache(token, "userCacheEntity");
		CacheUtil.removeCache(u.getId(), "userCacheEntityByid");
		
	}


	/**
	 * 
	 * @param token
	 * @return
	 */
	public UserEntity getUserByTokenFromCache(String token) {
		Element o = CacheUtil.getCacheObject(token, "userCacheEntity");
		UserEntity user = null;
		if (o != null) {
			user = (UserEntity) o.getObjectValue();
		}
		return user;
	}
	

	
	/**
	 * 
	 * @param token
	 * @return
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
	/**
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public TransactionsBeans saveAndupdateTransaction(TransactionsBeans t) throws Exception{
		
		//dao.save("WebappuserMapper.saveTransactions", t);
		CacheUtil.cacheSave(t.getId(), t, "Transactions");
		return t;
	}
	
	
	
public TransactionsBeans getTransactionFromCache(String id,String Cachename) throws Exception{
		
		//dao.save("WebappuserMapper.saveTransactions", t);
	Element o = CacheUtil.getCacheObject(id, Cachename);
	TransactionsBeans transactionsBeans=null;
	if(o!=null){
		transactionsBeans=(TransactionsBeans)o.getObjectValue();
	}
		return transactionsBeans;
	}
	
/**
 * 
 * @param id
 * @return
 * @throws Exception
 */
public TransactionsBeans getTransactionFromCacheById(String id) throws Exception{
	TransactionsBeans transactionsBeans=this.getTransactionFromCache(id, "Transactions");
	if(transactionsBeans==null){
		 transactionsBeans=this.getTransactionFromCache(id, "TransactionsLong");	
	}
     
	return transactionsBeans;
}
/**
 * 
 * @param transactionId
 * @return
 * @throws Exception
 */
public int checkTimeout(String transactionId) throws Exception{
	int res=0;
	TransactionsBeans tmp=this.getTransactionFromCacheById(transactionId);
	if(tmp==null){
		res=Const.zoningtimeout;
		return res;
	}
	if(this.getBananaFromCacheById(tmp.getBanana_id())==null){
		res=Const.bananaExpired;
	}
	
	return res;
	
}
/**
 * 
 * @param id
 */
public void removeTransactionsFromCache(String id){
	CacheUtil.removeCache(id, "Transactions");
	CacheUtil.removeCache(id, "TransactionsLong");
}
	
	// TransactionsLong
	// UserBanana


/**
 * 
 * @param phone
 * @return
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
	
	
	/**
	 * 
	 * @param banana_id
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
	
	/**
	 * 
	 * @param banana_id
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
	
	
	
	
	
	

	// topickeywords_banana
}
