package com.coderabbit.security.controller;

import com.coderabbit.security.dao.UserRepository;
import com.coderabbit.security.model.SecurityUser;
import com.coderabbit.security.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public String hello() throws JsonProcessingException {
        List<User> all = userRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user= (SecurityUser) authentication.getPrincipal();
        return new ObjectMapper().writeValueAsString(user);
    }

    /**
     * 根据权限
     * @return
     */
    @GetMapping("/auto")
    //权限字符串命名规范为：模块:功能:操作，例如：sys:user:edit
    //使用冒号分隔，对授权资源进行分类，如 sys:user:edit 代表 系统模块:用户功能:编辑操作
    @PreAuthorize("hasAnyAuthority('sys:test:test')")
    public String auto () {
        return "ok";
    }

    /**
     * 根据角色
     * @return
     */
    @GetMapping("/role")
    //角色前要加：ROLE_
    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    public String role () {
        return "ok";
    }
}
