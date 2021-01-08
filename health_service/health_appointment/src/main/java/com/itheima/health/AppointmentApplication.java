package com.itheima.health;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.itheima.health.mapper")
public class AppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class,args);
        System.out.println("----预约服务启动---");
    }
}
