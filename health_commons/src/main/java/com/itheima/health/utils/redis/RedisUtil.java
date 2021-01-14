package com.itheima.health.utils.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    private static DistributedRedisLock distributedRedisLock;

    public static void registerLock(DistributedRedisLock lock) {
        distributedRedisLock = lock;
    }

    private static int DETAULT_TIME_OUT = -1;

    public static void register(RedisTemplate<String, Object> template) {
        redisTemplate = template;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
    }

    public static RedisTemplate<String, Object> getInstance() {
        return redisTemplate;
    }

    public static void set(String key, Object value) {
        set(key, value, DETAULT_TIME_OUT);
    }

    public static void set(String key, Object value, long timeout) {
        set(key, value, timeout, TimeUnit.MINUTES);
    }

    public static void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        if (timeout > 0) {
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        // 判断是否缓存成功
        if (value != null) {
            Object obj = redisTemplate.opsForValue().get(key);
            int tryCount = 0;
            while (obj == null && tryCount <= 3) {
                tryCount++;
                if (timeout > 0)
                    redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
                else
                    redisTemplate.opsForValue().set(key, value);
                obj = redisTemplate.opsForValue().get(key);
            }
            if (obj == null) {
                throw new RuntimeException("redis cache error!");
            }
        }
    }

    public static void setHash(String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public static void setHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public static Map getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public static <T> T getHash(String key, String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    public static void delHash(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public static boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @param pattern 正则表达式
     */
    public static Set<String> keys(String pattern) {
        if (pattern.endsWith("*") == false) {
            pattern = pattern + "*";
        }
        return redisTemplate.keys(pattern);
    }

    /**
     * @param pattern 正则表达式
     */
    public static void removeKeys(String pattern) {
        redisTemplate.delete(keys(pattern));
    }

    /**
     * @param key
     * @return redis ttl 命令,返回剩余秒数
     */
    public static long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    public static void addToSet(String setKey, String value) {
        redisTemplate.opsForSet().add(setKey, value);
    }

    /**
     * 返回集合中是否包含某个值
     *
     * @param key
     * @return
     */
    public static boolean isMemberOfSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public static Long removeSetMember(String key, Object value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    public static Long getSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public static Set getMembersOfSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public static long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public static <T> Set<T> intersect(String key1, String key2) {
        return (Set<T>) redisTemplate.opsForSet().intersect(key1, key2);
    }

    public static boolean lock(String key) {
        return distributedRedisLock.lock(key, 10);
    }

    public static boolean lock(String key, long timeout) {
        return distributedRedisLock.lock(key, timeout);
    }

    /**
     * 解锁
     *
     * @param key
     */
    public static void unlock(String key) {
        distributedRedisLock.unlock(key);
    }
}
