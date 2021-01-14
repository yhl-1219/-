package com.itheima.health.utils.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.ResourceBundle;

public class SMSDemo {

    public static void main(String[] args) {
        ResourceBundle aliyunKey = ResourceBundle.getBundle("AliyunKey");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunKey.getString("smsAccessKey"), aliyunKey.getString("smsAccessKeySecret"));
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18841281217");
        request.putQueryParameter("SignName", "传智健康健康管理");
        request.putQueryParameter("TemplateCode", "SMS_209470154");
        request.putQueryParameter("TemplateParam", "{\"code\":\"3456\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
