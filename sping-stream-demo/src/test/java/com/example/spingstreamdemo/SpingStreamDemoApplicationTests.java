package com.example.spingstreamdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;

@SpringBootTest
class SpingStreamDemoApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        sendMethod();
    }

    @Autowired
    private StreamBridge streamBridge;

    public void sendMethod() {
        for (int i = 0; i < 10; i++) {
            streamBridge.send("analysePdf-out-0", "pdf"+i);
            streamBridge.send("jsh-out-0", "pdf"+i);
            System.out.println("************发送了message：");
        }
    }
}
