package com.jsh.shiro.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 2:42 下午
 */
@Data
public class User {
    /** id */
    private Long id;
    /** 用户名 */
    private String username;
    /** 名字 */
    private String name;
    /** 密码 */
    private String password;
    /** 是否是超级管理员 */
    private Boolean admin;
    /** 角色列表 */
    private List<Role> roleList;
}
