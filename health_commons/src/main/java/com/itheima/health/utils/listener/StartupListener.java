package com.itheima.health.utils.listener;

import com.itheima.health.utils.redis.DistributedRedisLock;
import com.itheima.health.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * spring ioc容器初始化完成后自动执行
 * 一般用于项目初始化
 *
 * @author wangweili
 */
@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private DistributedRedisLock distributedRedisLock;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            RedisUtil.register(redisTemplate);
            RedisUtil.registerLock(distributedRedisLock);
            log.info("=========redis=========");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
