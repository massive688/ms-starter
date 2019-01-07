package tp.ms.common.batis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/*
 * http://blog.csdn.net/xiadi934/article/details/50786293
 */
public class MsRedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
        MsMybatisRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}