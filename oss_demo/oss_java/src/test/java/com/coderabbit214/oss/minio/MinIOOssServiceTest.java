package com.coderabbit214.oss.minio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Mr_J
 * @Date: 2022/3/11 4:29 下午
 */
@SpringBootTest
class MinIOOssServiceTest {

    @Autowired
    private MinIOOssService minIOOssService;

    @Test
    void uploadFile() {
    }

    @Test
    void existFile() {
    }

    @Test
    void getPolicy() {
    }

    @Test
    void getExpiration() {
        String objectName = "test/6bab71a2-0eab-4ce0-bed4-6918c77adc24_%E6%88%AA%E5%B1%8F2022-03-09%20%E4%B8%8B%E5%8D%884.21.45.png";
        System.out.println(minIOOssService.getExpiration(objectName));
    }
}