package com.itheima.health;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class TMApplication {

    public static void main(String[] args) {
        SpringApplication.run(TMApplication.class,args);
        System.out.println("---分布式事务管理器启动--");
    }

}