package com.coderabbit.threadpooldemo.controller;

import com.coderabbit.threadpooldemo.service.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mr_J
 * @Date: 2022/4/8 11:24 上午
 */
@RestController
public class Test {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/test")
    @Async
    public String test(){
        try {
            asyncTask.doTask1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
