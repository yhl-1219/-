package com.itheima.health.utils.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIA2KE6onERO5";
        String accessKeySecret = "raxTWs3twt1k9ESJrS2kUyrDUMFoG";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("d:\\3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg");
        ossClient.putObject("itcast117", "aa.jpg", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();


    }
}
