package com.jsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mr_J
 * @Date: 2021/10/22 11:18 上午
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("test");
        return "test";
    }
}
