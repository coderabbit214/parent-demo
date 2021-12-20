package com.coderabbit.security.service;


import com.coderabbit.security.model.SecurityUser;

public interface LoginService {
    /**
     * 根据用户名查找
     */
    SecurityUser loadByUsername(String username);
}
