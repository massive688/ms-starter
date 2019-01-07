package tp.ms.common.batis.cache;
/*
 * com.my.web.common.vo.redis.cache.MybatisRedisCache 
 * 
 * <!-- *映射语句 -->
 * <cache type="com.my.web.common.vo.redis.cache.MybatisRedisCache"/>
 */
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.exceptions.JedisConnectionException;

//import redis.clients.jedis.exceptions.JedisConnectionException;
public class MsMybatisRedisCache implements Cache {

	private static final Logger LOG = LoggerFactory.getLogger(MsMybatisRedisCache.class);

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	
	private static RedisConnectionFactory jedisConnectionFactory;

	private final String id;

	public MsMybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		LOG.info("Redis Cache id " + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisConnection connection = null;
		try {
			LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(); // 借用spring_data_redis.jar中的JdkSerializationRedisSerializer.class
			connection.set(serializer.serialize(key), serializer.serialize(value)); // 利用其序列化方法将数据写入redis服务的缓存中

		} catch (JedisConnectionException e) {
			e.printStackTrace();
			
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(); // 借用spring_data_redis.jar中的JdkSerializationRedisSerializer.class
			result = serializer.deserialize(connection.get(serializer.serialize(key))); // 利用其反序列化方法获取值
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		RedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void clear() {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	public static void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
		MsMybatisRedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
}
