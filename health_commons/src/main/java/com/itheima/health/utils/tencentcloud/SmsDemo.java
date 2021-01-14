package com.itheima.health.utils.tencentcloud;

public class SmsDemo {

    public static void main(String[] args) {
        System.out.println(SendSms.sendVerifyMsg("17601512453"));
        SendSms.sendSuccessMsg("17601512453");
    }

}
