package com.itheima.health.config;

import com.itheima.health.service.SetmealService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangweili
 */
@JobHandler(value = "heima.setmeal.clean.img.job")
@Component
public class MyJob extends IJobHandler {

    @Resource
    private SetmealService setmealService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("clearOssObject!!!!!");
//        setmealService.clearOssImg();
        return SUCCESS;
    }
}
