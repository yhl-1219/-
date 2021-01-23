package com.itheima.health.config;

import com.itheima.health.service.OrderSettingService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangweili
 */
@JobHandler(value = "health.ordersetting.history.date.job")
@Component
public class MyClearHistoryJob extends IJobHandler {

    @Resource
    private OrderSettingService orderSettingService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("历史数据清理了一次");
        orderSettingService.clearOrderSettingHistoryData();
        return SUCCESS;
    }
}
