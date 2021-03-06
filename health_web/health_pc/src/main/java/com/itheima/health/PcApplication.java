package com.itheima.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangweili 
 */
@Slf4j
@SpringBootApplication
public class PcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcApplication.class, args);
        log.info("===PC端启动===");
    }

}
