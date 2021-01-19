package com.yqq.commons.cached.service.impl;

import com.yqq.commons.cached.service.RedisService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * <功能描述>
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Resource(name = "redisTemplate")
	private RedisTemplate<Object, Object> redisTemplate;

	private static final Long LOCK_EXPRETIME = 60L;

	public static final String UNLOCK_LUA;
	/**
	 * 存储随机数
	 **/
	private static final ThreadLocal<String> local = new ThreadLocal<>();

	static {
		StringBuilder sb = new StringBuilder();
		sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
		sb.append("then ");
		sb.append("    return redis.call(\"del\",KEYS[1]) ");
		sb.append("else ");
		sb.append("    return 0 ");
		sb.append("end ");
		UNLOCK_LUA = sb.toString();
	}

	/**
	 * 重载方法
	 *
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public void updateStringKey(String key, Object value, Long expire) {
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value, expire, TimeUnit.SECONDS);
	}

	/**
	 * 重载方法
	 *
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public void updateStringKey(String key, Object value) {
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}

	/**
	 * 重载方法
	 *
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public Object getStringKey(String key) {
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	@Override
	public void delStringKey(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public String getKey(final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(final RedisConnection connection) {
				return connection.get(redisTemplate.getStringSerializer().serialize(key)) == null ? "" : new String(connection.get(redisTemplate.getStringSerializer().serialize(key)));
			}
		});
	}

	/**
	 * @param mapkey
	 * @param hashkey
	 * @return
	 */
	@Override
	public String getHashKey(final String mapkey, final String hashkey) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(final RedisConnection connection) {
				return connection.hGet(redisTemplate.getStringSerializer().serialize(mapkey), redisTemplate.getStringSerializer().serialize(hashkey)) == null ? "" : new String(connection.hGet(redisTemplate.getStringSerializer().serialize(mapkey), redisTemplate.getStringSerializer().serialize(hashkey)));
			}
		});
	}

	@Override
	public void setHashKey(String mapkey, String hashkey, String value, long secods) {
		redisTemplate.execute(new RedisCallback<Void>() {
			@Override
			public Void doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByteArray = redisTemplate.getStringSerializer().serialize(mapkey);
				byte[] fieldByteArray = redisTemplate.getStringSerializer().serialize(hashkey);
				byte[] valueByteArray = redisTemplate.getStringSerializer().serialize(value);
				connection.hSet(keyByteArray, fieldByteArray, valueByteArray);
				connection.expire(keyByteArray, secods);
				return null;
			}
		});
	}

	@Override
	public Long incrementHashKey(String mapkey, String hashkey, Long delta) {
		HashOperations<Object, Object, Long> hashOpts = redisTemplate.opsForHash();
		return hashOpts.increment(mapkey, hashkey, delta);
	}

	public void setJsonString(String key, Object value, Long expire) {
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Serializable.class));
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value, expire, TimeUnit.SECONDS);
	}

	@Override
	public Long addToSet(String key, String value) {
		return redisTemplate.opsForSet().add(key, value);
	}

	@Override
	public Long addCollectionToSet(String key, List<String> values, Long expire) {
		Long ret = redisTemplate.opsForSet().add(key, values.toArray());
		redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		return ret;
	}

	@Override
	public Boolean isMemberOfSet(String key, String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public Boolean isMemberOfSetN(String key, String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByteArray = redisTemplate.getStringSerializer().serialize(key);
				byte[] fieldByteArray = redisTemplate.getStringSerializer().serialize(value);
				return connection.sIsMember(keyByteArray, fieldByteArray);
			}
		});
	}

	@Override
	public Boolean isStringExits(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public Long incrementKey(String key, long count) {
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		return valueOperations.increment(key, count);
	}

	@Override
	public Long decrementKey(String key) {
		ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
		return valueOperations.decrement(key);
	}

	@Override
	public void leftPushKey(String key, String value) {
		ListOperations<Object, Object> operations = redisTemplate.opsForList();
		operations.leftPush(key, value);
	}

	@Override
	public Object rightPopKey(String key) {
		ListOperations<Object, Object> operations = redisTemplate.opsForList();
		return operations.rightPop(key);
	}

	@Override
	public void remFromSet(String key, String value) {
		this.redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sRem(redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(value));
			}
		});
	}

	@Override
	public void setIntKey(String key, int value) {
		RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		counter.set(value);
	}

	@Override
	public Long getIntKey(String key) {
		RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		return counter.get();

	}

	@Override
	public Object rightBPopKey(String key, long timeout, TimeUnit unit) {
		ListOperations<Object, Object> operations = redisTemplate.opsForList();
		return operations.rightPop(key, timeout, unit);
	}

	@Override
	public Long getKeyExpire(String key) {
		ListOperations<Object, Object> operations = redisTemplate.opsForList();
		return operations.getOperations().getExpire(key);
	}

	@Override
	public void lock(String key) {
		boolean b = tryLock(key);
		if (b) {
			return;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock(key);
	}

	@Override
	public Boolean tryLock(String key) {
		String uuid = UUID.randomUUID().toString();
		return tryLock(key, uuid);
	}

	@Override
	public Boolean tryLock(String key, String value) {
		return tryLock(key, value, LOCK_EXPRETIME, TimeUnit.SECONDS);
	}

	@Override
	public Boolean tryLock(String key, long timeout, TimeUnit unit) {
		String uuid = UUID.randomUUID().toString();
		return tryLock(key, uuid, timeout, unit);
	}

	@Override
	public Boolean tryLock(String key, String value, long timeout, TimeUnit unit) {
		Boolean result = this.redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByteArray = redisTemplate.getStringSerializer().serialize(key);
				byte[] valueByteArray = redisTemplate.getStringSerializer().serialize(value);
				Object obj = connection.execute("set", keyByteArray,
						valueByteArray,
						"NX".getBytes(StandardCharsets.UTF_8),
						"EX".getBytes(StandardCharsets.UTF_8),
						String.valueOf(timeout).getBytes(StandardCharsets.UTF_8));
				return obj != null;
			}
		});
		if (result) {
			local.set(value);
		}
		return result;
	}

	@Override
	public void unLock(String key) {
		Long result = this.redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByteArray = redisTemplate.getStringSerializer().serialize(key);
				byte[] valueByteArray = redisTemplate.getStringSerializer().serialize(local.get());
				return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.fromJavaType(Long.class), 1, keyByteArray, valueByteArray);
			}
		});
		if (result > 0) {
			local.remove();
		}
	}
	

    /**
     * 判断 key是否存在value
     * @param key
     * @return true 存在 false 不存在
     */
    @Override
    public void delKey(String key)
    {
       redisTemplate.delete(key);
    }
    
   
    @Override
    public void delKeys(String keys)
    {
        Set<Object> dkeys = redisTemplate.keys(keys); 
        redisTemplate.delete(dkeys);
    }
    
    public boolean isSetMember(String key, Object value){
        SetOperations<Object, Object> ops = redisTemplate.opsForSet();
        return ops.isMember(key,value);
    }
    
    public void updateSetMembers(String key, List<String> values, Long expire){
        BoundSetOperations<Object, Object> bsp = redisTemplate.boundSetOps(key);
        values.forEach(item -> bsp.add(item));
        bsp.expire(expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
