package com.itheima.health;


import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangweili 
 */
@SpringBootApplication
@MapperScan(basePackages = "com.itheima.health.mapper")
@EnableDistributedTransaction
public class AppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class,args);
        System.out.println("----预约服务启动---");
    }
}
