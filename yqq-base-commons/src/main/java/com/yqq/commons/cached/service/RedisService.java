package com.yqq.commons.cached.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <功能描述>
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public interface RedisService {

	/**
	 * <功能描述>
	 *
	 * @param key
	 * @param value
	 */
	public void updateStringKey(String key, Object value);

	/**
	 * <功能描述>
	 *
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void updateStringKey(String key, Object value, Long expire);

	/**
	 * <功能描述>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getStringKey(String key);

	/**
	 * <功能描述>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public void delStringKey(String key);

	/**
	 * @param mapkey
	 * @param hashkey
	 * @return
	 */
	public String getHashKey(final String mapkey, final String hashkey);

	/**
	 * 设置hash里的field值。
	 *
	 * @param mapkey
	 * @param field
	 * @param value
	 */
	public void setHashKey(final String mapkey, final String field, final String value, final long seconds);

	public Long incrementHashKey(final String mapkey, final String hashkey, final Long delta);

	public String getKey(final String key);

	/**
	 * 新增到字符串值到set里。
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long addToSet(final String key, final String value);

	/**
	 * 新增到字符串值到set里。
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long addCollectionToSet(final String key, final List<String> values, Long expire);

	/**
	 * 从set里删除字符串值。
	 *
	 * @param key
	 * @param value
	 */
	public void remFromSet(final String key, final String value);

	/**
	 * 判断字符串value是否在set中。
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean isMemberOfSet(final String key, final String value);


	public Boolean isMemberOfSetN(final String key, final String value);

	/**
	 * 判断指定key是否存在
	 *
	 * @param key
	 * @return
	 */
	public Boolean isStringExits(String key);

	public Long incrementKey(String key, long count);

	public Long decrementKey(String key);

	public void leftPushKey(String key, String value);

	public Object rightPopKey(String key);

	public void setIntKey(String key, int value);

	public Long getIntKey(String key);

	public Object rightBPopKey(String key, long timeout, TimeUnit unit);

	public Long getKeyExpire(String key);

	/**
	 * 分布式锁（获取不到会阻塞）
	 *
	 * @param key
	 */
	public void lock(String key);

	/**
	 * 分布式锁（获取不到返回false）
	 *
	 * @param key
	 * @return
	 */
	public Boolean tryLock(String key);

	/**
	 * 分布式锁（获取不到返回false）
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean tryLock(String key, String value);

	/**
	 * 分布式锁（获取不到返回false）
	 *
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Boolean tryLock(String key, long timeout, TimeUnit unit);

	/**
	 * 分布式锁（获取不到返回false）
	 *
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Boolean tryLock(String key, String value, long timeout, TimeUnit unit);

	/**
	 * 释放锁
	 *
	 * @param key
	 */
	public void unLock(String key);
	

    
    /**
     * 
     * 删除缓存
     * @param key
     * @return
     */
    void delKey(String key);
    /**
     * 
     * 通配符删除缓存
     * @param keys
     * @return
     */
    void delKeys(String keys);

    public boolean isSetMember(String key, Object value);

    public void updateSetMembers(String key, List<String> values, Long expire);
    
    public boolean existsKey(String key);
}
