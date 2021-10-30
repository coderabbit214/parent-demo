package com.jsh.reflections.controller;

import com.jsh.reflections.listener.InterfaceStatisticsInitListener;
import com.jsh.reflections.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.applet.context.InitialJNLPExecutionContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author: Mr_J
 * @Date: 2021/10/29 6:49 下午
 */

@Api(tags = {"test类"})
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "test方法")
    @GetMapping("/test")
    public void test(@RequestParam("id") long id,int age){

    }
}
