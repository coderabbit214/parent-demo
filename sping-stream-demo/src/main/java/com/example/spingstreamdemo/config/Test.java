package com.example.spingstreamdemo.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @Author: Mr_J
 * @Date: 2022/5/11 17:18
 */
@Configuration
public class Test {
    @Bean("jsh")
    public Consumer<String> jsh() {
        return str -> {
            System.out.println("test: " + str);
        };
    }
    @Bean("analysePdf")
    public Consumer<Message<String>> analysePdf() {
        return message -> {
            Channel channel = message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
            Long deliveryTag = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);

            String payload = message.getPayload();
            System.out.println("analysePdf: " + payload);
//            throw new RuntimeException();
        };
    }
}
