package com.coderabbit214.oss.minio;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mr_J
 */
@Component
public class MinIOConstantPropertiesUtils implements InitializingBean {

    @Value("${oss.minio.endpoint}")
    private String endpoint;
    @Value("${oss.minio.keyid}")
    private String keyId;
    @Value("${oss.minio.keysecret}")
    private String keySecret;
    @Value("${oss.minio.bucketname}")
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