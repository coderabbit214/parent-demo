package com.coderabbit.threadpooldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class ThreadPoolDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolDemoApplication.class, args);
    }

}
