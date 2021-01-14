package com.itheima.health.utils.tencentcloud;

import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisMessageConstant;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
// 导入 SMS 模块的 client
import com.tencentcloudapi.sms.v20190711.SmsClient;
// 导入要请求接口对应的 request response 类
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


/**
 * Tencent Cloud Sms Sendsms
 * https://cloud.tencent.com/document/product/382/38778
 *
 * @author wangweili
 */
public class SendSms {

    public static final ResourceBundle RB = ResourceBundle.getBundle("AliyunKey");

    public static final Credential CRED = new Credential(RB.getString("tencentId"), RB.getString("tencentValue"));

    public static Integer sendVerifyMsg(String telephone) {
        try {
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(CRED, "", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String appid = "1400473690";
            req.setSmsSdkAppid(appid);
            String sign = "卷书成船";
            req.setSign(sign);
            String templateID = "844026";
            req.setTemplateID(templateID);
            String[] phoneNumbers = {"+86" + telephone};
            req.setPhoneNumberSet(phoneNumbers);
            int o = new Random().nextInt(900000) + 100000;
            String[] templateParams = {String.valueOf(o)};
            req.setTemplateParamSet(templateParams);
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res.getRequestId());
            return o;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean sendSuccessMsg(String telephone) {
        try {
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(CRED, "", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String appid = "1400473690";
            req.setSmsSdkAppid(appid);
            String sign = "卷书成船";
            req.setSign(sign);
            String templateID = "844027";
            req.setTemplateID(templateID);
            String[] phoneNumbers = {"+86" + telephone};
            req.setPhoneNumberSet(phoneNumbers);
            String[] templateParams = {};
            req.setTemplateParamSet(templateParams);
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res.getRequestId());
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        } finally {
            return true;
        }
    }
}