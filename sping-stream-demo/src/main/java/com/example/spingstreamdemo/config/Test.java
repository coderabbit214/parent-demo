package com.example.spingstreamdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public Consumer<String> analysePdf() {
        return str -> {
            System.out.println("analysePdf: " + str);
//            throw new RuntimeException();
        };
    }
}
