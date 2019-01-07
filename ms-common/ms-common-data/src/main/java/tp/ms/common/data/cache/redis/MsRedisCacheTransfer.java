package tp.ms.common.data.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * http://blog.csdn.net/xiadi934/article/details/50786293
 * @描述: 静态注入中间类
 * @版权: Copyright (c) 2016 
 * @作者: xiad
 * @版本: 1.0 
 * @创建日期: 2016年3月2日 
 * @创建时间: 下午8:02:57
 */
public class MsRedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
        MsMybatisRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}