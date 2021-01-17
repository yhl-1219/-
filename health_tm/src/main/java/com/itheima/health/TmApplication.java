package com.itheima.health;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangweili
 */
@SpringBootApplication
@EnableTransactionManagerServer
@Slf4j
public class TmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmApplication.class,args);
        log.info("---分布式事务管理器启动--");
    }

}