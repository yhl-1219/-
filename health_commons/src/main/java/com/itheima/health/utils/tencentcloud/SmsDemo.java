package com.itheima.health.utils.tencentcloud;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangweili
 */
@Slf4j
public class SmsDemo {

    public static void main(String[] args) {
        log.info(String.valueOf(SendSms.sendVerifyMsg("17601512453")));
        SendSms.sendSuccessMsg("17601512453");
    }

}
