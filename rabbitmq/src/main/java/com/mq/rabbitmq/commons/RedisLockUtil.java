package com.mq.rabbitmq.commons;

import com.mq.rabbitmq.supports.redis.RedisService;
import com.mq.rabbitmq.supports.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
* @Author: Ben
* @Date: 2019-04-19
* @Description: Redis分布式锁
*/
public class RedisLockUtil {

    private static final int defaultExpire=60;

    @Autowired
    private RedisTemplate redisTemplate;

    private RedisLockUtil(){

    }

    /**
     * 加锁
     * @param key redis key
     * @param expire 过期时间，单位秒
     * @return true:加锁成功，false，加锁失败
     */
    public static boolean lock(String key, int expire){
        RedisService redisService = SpringContextUtils.getBean(RedisService.class);
        long status=redisService
    }
}
