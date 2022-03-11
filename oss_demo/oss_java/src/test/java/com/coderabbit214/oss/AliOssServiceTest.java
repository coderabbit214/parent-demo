package com.coderabbit214.oss;

import com.coderabbit214.oss.aliyun.AliOssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Mr_J
 * @Date: 2022/3/10 5:17 下午
 */
@SpringBootTest
class AliOssServiceTest {

    @Autowired
    private AliOssService aliOssService;

    @Test
    void getExpiration() {
        String expiration = aliOssService.getExpiration("https://memect-finsense.oss-cn-beijing.aliyuncs.com/test/8c23f4d1-f9c2-42e9-8837-4651d825b271_截屏2022-03-09 下午4.21.45 (2).png");
        System.out.println(expiration);
    }
}