package com.itheima.health.utils.redis;

import java.util.Random;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/22 2:52 下午
 */
public class RandomRedisExpiredTime {

    public static Integer getExpireTime() {
        return new Random().nextInt(20) + 10;
    }

}
