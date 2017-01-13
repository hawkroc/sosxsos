package com.sosxsos.ssm.service;

import com.sosxsos.ssm.entity.BananaEntity;
import com.sosxsos.ssm.entity.TransactionsEntity;
import com.sosxsos.ssm.entity.UserEntity;

public interface CacheServiceRedis {

	/**
	 * 
	 * @param code
	 * @return
	 */
	boolean checkCodeByFrontEnd(int code);

	void updateCacheUse(UserEntity u, String token);

	void removeUserCache(String token, UserEntity u);

	/**
	 * 
	 * @param token
	 * @return
	 */
	UserEntity getUserByTokenFromCache(String token);

	/**
	 * 
	 * @param token
	 * @return
	 */
	UserEntity getUserByTokenFromCache(int userid);

	// Transactions
	/**
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	TransactionsEntity saveAndupdateTransaction(TransactionsEntity t) throws Exception;

	TransactionsEntity getTransactionFromCache(String id, String Cachename) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TransactionsEntity getTransactionFromCacheById(String id) throws Exception;

	/**
	 * 
	 * @param transactionId
	 * @return
	 * @throws Exception
	 */
	int checkTimeout(String transactionId) throws Exception;

	/**
	 * 
	 * @param id
	 */
	void removeTransactionsFromCache(String id);

	/**
	 * 
	 * @param phone
	 * @return
	 */
	BananaEntity getBananaFromCache(String phone);

	// UserBananaId
	BananaEntity getBananaFromCacheById(long banana_id);

	/**
	 * 
	 * @param banana_id
	 */
	BananaEntity updateBananaFromCacheByid(long banana_id, int status);

	/**
	 * 
	 * @param banana_id
	 */
	void removeBananaFromCacheByid(long banana_id);

}