package com.jsh.shiro.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:17 下午
 */
@Data
public class Role {
    /** 角色id */
    private Long id;
    /** 角色名称 */
    private String name;
    /** 角色编号 */
    private String sn;
    /** 角色权限列表 */
    private List<Permission> permissionList;


}
