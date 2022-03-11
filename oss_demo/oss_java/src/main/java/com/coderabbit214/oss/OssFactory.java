package com.coderabbit214.oss;

import com.coderabbit214.oss.aliyun.AliOssService;
import com.coderabbit214.oss.minio.MinIOOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Mr_J
 * @Date: 2022/3/10 2:35 下午
 */
@Component
public class OssFactory {

    @Value("${oss.mode}")
    private String endpoint;

    public OssInterface getOssService() {
        if ("ALIYUN".equals(endpoint)) {
            return new AliOssService();
        } else if ("MINIO".equals(endpoint)) {
            return new MinIOOssService();
        } else {
            throw new RuntimeException("没有这个选项，宝");
        }
    }
}
