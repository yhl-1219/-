package com.itheima.health.utils.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.ResourceBundle;

/**
 * 短信发送工具类
 * 
 * @author wangweili
 */
public class SmsUtils {
    /**
     * 发送短信验证码
     */
    public static final String VALIDATE_CODE = "SMS_209470154";
    /**
     * 体检预约成功通知
     */
    public static final String ORDER_NOTICE = "SMS_209560085";


    /**
     * 发送短信验证码
     *
     * @param phoneNumbers
     * @param param
     * @throws ClientException
     */
    public static void validUserTelephone(String phoneNumbers, String param) {

        try {
            ResourceBundle aliyunKey = ResourceBundle.getBundle("AliyunKey");
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunKey.getString("smsAccessKey"), aliyunKey.getString("smsAccessKeySecret"));
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNumbers);
            request.putQueryParameter("SignName", "VWWL");
            request.putQueryParameter("TemplateCode", VALIDATE_CODE);
            request.putQueryParameter("TemplateParam", "{\"code\":\"" + param + "\"}");

            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static void orderSuccessMessage(String phoneNumbers, String param) throws ClientException {
        ResourceBundle aliyunKey = ResourceBundle.getBundle("AliyunKey");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunKey.getString("smsAccessKey"), aliyunKey.getString("smsAccessKeySecret"));
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "VWWL");
        request.putQueryParameter("TemplateCode", ORDER_NOTICE);
        request.putQueryParameter("TemplateParam", "{\"submittime\":\"" + param + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
