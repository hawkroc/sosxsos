package com.sosxsos.ssm.cache;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.sosxsos.ssm.util.ProtoStuffSerializerUtil;

/**
 * redis缓存
 * 
 * @author sosxsos10627
 *
 */
@Component
public class RedisCache {

	public final static String CAHCENAME = "cache";// 缓存名
	public final static int CAHCETIME = 60;// 默认缓存时间

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public <T> boolean putCache(final String key, T obj) {
		return this.putObjectCacheWithExpireTime(key, obj, 0);
	}

	/**
	 * 
	 * @param key
	 * @param obj
	 * @param expireTime
	 * @return
	 */
	public <T> boolean putCacheWithExpireTime(final String key, T obj, final long expireTime) {
		return this.putObjectCacheWithExpireTime(key, obj, expireTime);
	}

	/**
	 * 
	 * @param key
	 * @param obj
	 * @param expireTime
	 * @return
	 */
	private <T> boolean putObjectCacheWithExpireTime(final String key, T obj, final long expireTime) {
		final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
		return this.putObjectByteCacheWithExpireTime(key, bvalue, expireTime);
	}

	/**
	 * 
	 * @param key
	 * @param objList
	 * @return
	 */
	public <T> boolean putListCache(final String key, List<T> objList) {
		return this.putObjectListCacheWithExpireTime(key, objList, 0);
	}

	/**
	 * 
	 * @param key
	 * @param objList
	 * @param expireTime
	 * @return
	 */
	public <T> boolean putListCacheWithExpireTime(final String key, List<T> objList, final long expireTime) {

		return this.putObjectListCacheWithExpireTime(key, objList, expireTime);
	}

	/**
	 * 
	 * @param key
	 * @param objList
	 * @param expireTime
	 * @return
	 */
	private <T> boolean putObjectListCacheWithExpireTime(final String key, List<T> objList, final long expireTime) {
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		return this.putObjectByteCacheWithExpireTime(key, bvalue, expireTime);
	}

	

	/**
	 * 
	 * @param key
	 * @param targetClass
	 * @return
	 */
	public <T> T getCache(final String key, Class<T> targetClass) {
		return ProtoStuffSerializerUtil.deserialize(this.getObjectFromCache(key), targetClass);
	}

	/**
	 * 
	 * @param key
	 * @param targetClass
	 * @return
	 */
	public <T> List<T> getListCache(final String key, Class<T> targetClass) {
		return ProtoStuffSerializerUtil.deserializeList(this.getObjectFromCache(key), targetClass);
	}
	

	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	/**
	 * 清空所有缓存
	 * 
	 * @param key
	 */
	public void clearCache() {
		deleteCacheWithPattern(RedisCache.CAHCENAME + "|*");
	}
	
	/**
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public <T> boolean updateCache(final String key, T obj,final long expireTime) {
		deleteCache(key);
		return this.putCacheWithExpireTime(key, obj, expireTime);
	}
	
	
	
	/**
	 * 
	 * @param key
	 * @param objList
	 * @param expireTime
	 * @return
	 */
	public <T> boolean updateListCacheWithExpireTime(final String key, List<T> objList, final long expireTime) {
		deleteCache(key);
		return this.putListCacheWithExpireTime(key, objList, expireTime);
	}

	/**
	 * 
	 * @param key
	 * @param targetClass
	 * @return
	 */
	private final byte[] getObjectFromCache(final String key) {

		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return result;

	}
	
	
	/**
	 * 
	 * @param key
	 * @param obj
	 * @param expireTime
	 * @return
	 */
	private final <T> boolean putObjectByteCacheWithExpireTime(final String key, byte[] obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = obj;
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				if (expireTime == 0) {
					connection.setEx(bkey, expireTime, bvalue);
				} else {
					return connection.setNX(bkey, bvalue);
				}

				return true;
			}
		});
		return result;
	}
	
	
	
}
