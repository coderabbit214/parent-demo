package com.coderabbit214.oss;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mr_J
 */
@Component
public class OssConstantPropertiesUtils implements InitializingBean {

    @Value("${oss.dir}")
    private String dir;
    @Value("${oss.updateExpiryTime}")
    private Integer updateExpiryTime;
    @Value("${oss.downloadExpiryTime}")
    private Integer downloadExpiryTime;

    public static String DIR;
    public static Integer UPDATE_EXPIRY_TIME;
    public static Integer DOWNLOAD_EXPIRY_TIME;

    @Override
    public void afterPropertiesSet() {
        DIR = dir;
        UPDATE_EXPIRY_TIME = updateExpiryTime;
        DOWNLOAD_EXPIRY_TIME = downloadExpiryTime;
    }
}