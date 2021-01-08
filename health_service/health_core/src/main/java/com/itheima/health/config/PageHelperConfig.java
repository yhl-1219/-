package com.itheima.health.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelperConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
         //   分页查询  自动生成   limit   count 2条sql语句
        return new PaginationInterceptor();
    }

}
