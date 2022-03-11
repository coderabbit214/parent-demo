package com.coderabbit214.oss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Mr_J
 * @Date: 2022/3/10 10:28 上午
 */
@SpringBootTest
class OssServiceTest {

    @Autowired
    private OssInterface ossService;

    @Test
    void uploadFileAvatar() {
        //用swagger吧，方便一下
    }

    @Test
    void existFile() {
        System.out.println(ossService.existFile("37d4010e-4b67-4599-a4c8-f836fc8266c2截屏2022-03-09 下午4.21.45.png"));
    }
}