package com.itheima.health.utils.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wangweili
 */
@Component
public class DistributedRedisLock {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 加锁
     */
    public Boolean lock(String lockName,long timeout) {
        try {
            if (redissonClient == null) {
                return false;
            }
            RLock lock = redissonClient.getLock(lockName);
            // 锁10秒后自动释放，防止死锁
            lock.lock(timeout, TimeUnit.SECONDS);
            // 加锁成功
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 释放锁
    public Boolean unlock(String lockName) {
        try {
            if (redissonClient == null) {
                return false;
            }
            RLock lock = redissonClient.getLock(lockName);
            lock.unlock();
            // 释放锁成功
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
