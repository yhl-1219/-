package com.itheima.health.utils.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信发送工具类
 */
public class SmsUtils {
    public static final String VALIDATE_CODE = "SMS_192541299";//发送短信验证码
    public static final String ORDER_NOTICE = "SMS_192571486";//体检预约成功通知

    /**
     * 发送短信验证码
     * @param phoneNumbers
     * @param param

     * @throws ClientException
     */
    public static void validUserTelephone(String phoneNumbers,String param) {

        try {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAILlflJAmVwH25", "vxbZLEZVsNh7y5eljTIzbFWbrSTexN");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "传智健康黑马唐");
        request.putQueryParameter("TemplateCode", VALIDATE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+param+"\"}");

            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    public static void orderSuccessMessage(String phoneNumbers,String param) throws ClientException{
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fqa5u2yHbESK9eLVjsN", "r3dVpNNKW2nfJelHLnN9FOZviE0ltm");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "传智健康黑马唐");
        request.putQueryParameter("TemplateCode", ORDER_NOTICE);
        request.putQueryParameter("TemplateParam", "{\"submittime\":\""+param+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //1234
    public static void main(String[] args) throws ClientException {
        // SMSUtils.sendShortMessage("SMS_169641911","15688268846","2345");
    }
}
