package com.jsh.shiro.controller;

import com.jsh.shiro.result.JSonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:33 下午
 */
@RestController
public class Login {

    @GetMapping  ("/login")
    public JSonResult login(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return JSonResult.error("用户名或密码错误");
        }
        subject.login(usernamePasswordToken);
        return JSonResult.success(null);
    }

    @RequiresPermissions(value = {"department:list","部门列表"},logical = Logical.OR)
    @GetMapping("/test")
    public JSonResult test(){
        return JSonResult.success("验证成功");
    }
}
