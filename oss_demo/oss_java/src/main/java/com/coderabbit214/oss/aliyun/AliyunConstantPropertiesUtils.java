package com.coderabbit214.oss.aliyun;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mr_J
 */
@Component
public class AliyunConstantPropertiesUtils implements InitializingBean {

    @Value("${oss.aliyun.endpoint}")
    private String endpoint;
    @Value("${oss.aliyun.keyid}")
    private String keyId;
    @Value("${oss.aliyun.keysecret}")
    private String keySecret;
    @Value("${oss.aliyun.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}