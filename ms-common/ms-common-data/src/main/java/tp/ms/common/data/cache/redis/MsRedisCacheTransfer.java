package tp.ms.common.data.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

public class MsRedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
        MsMybatisRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}