package com.example.spingstreamdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

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
            Message<String> message = new GenericMessage<>("pdf"+i);
            streamBridge.send("analysePdf-out-0", message);
//            streamBridge.send("jsh-out-0", "pdf"+i);
            System.out.println("************发送了message：");
        }
    }
}
