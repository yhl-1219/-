//package com.itheima.health.utils.alipay;
//
//import com.alipay.easysdk.factory.Factory;
//import com.alipay.easysdk.factory.Factory.Payment;
//import com.alipay.easysdk.kernel.Config;
//import com.alipay.easysdk.kernel.util.ResponseChecker;
//import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
//
//import java.util.ResourceBundle;
//
///**
// * @author wangweili
// * @version 1.0
// * @date 2021/1/21 10:36 上午
// */
//public class AlipayTest {
//
//    public static void main(String[] args) throws Exception {
//        // 1. 设置参数（全局只需设置一次）
//        Factory.setOptions(getOptions());
//        try {
//            // 2. 发起API调用（以创建当面付收款二维码为例）
//            AlipayTradePrecreateResponse response = Payment.FaceToFace()
//                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
//            // 3. 处理响应或异常
//            if (ResponseChecker.success(response)) {
//                System.out.println("调用成功");
//            } else {
//                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
//            }
//        } catch (Exception e) {
//            System.err.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
//
//    private static Config getOptions() {
//        Config config = new Config();
//        config.protocol = "https";
//        config.gatewayHost = "openapi.alipay.com";
//        config.signType = "RSA2";
//
//        config.appId = "2021000117603613";
//
//        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
//        config.merchantPrivateKey = ResourceBundle.getBundle("AliyunKey").getString("alipaySecret");
//
//        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        //config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        //config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->"
//        //config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
//
//        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
//        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodpk7an5kaf/SvW+tMf5R4UAuB1GtMNZ5iRUU438trVsfOkS7UwE5gRpwGyeRqbiqhHgobdzEsWzU8eo1HQ6KZ9EvTRU6p7AlRjj8JyBckiI0tsKmgjqhhD1tg5BhhHQnmROlby5NBFvIpUhF4jiASqlphrDxCQSK2w9B2AtPOZZwcYpnAgla3lpMAhqbNOH6uiFVkd79Wn3uQz5szwy16TAKbH4zMOatZcfjQsOvl64COdLZwoF705hfV51EO4oWdLnBxWf9HvkOGd5CatrtSAiiEBuqP8Xrm8zqaoGnNkHTwUJoifja1+R57QKY4WQS4pVqtERbPo8uQa1Lh5c/QIDAQAB";
//
//        //可设置异步通知接收服务地址（可选）
////        config.notifyUrl = "localhost:8082";
//
//        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
////        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";
//
//        return config;
//    }
//
//}
