package com.jsh.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:19 下午
 */
@Data
@AllArgsConstructor
public class Permission {
    /** 权限id */
    private Long id;
    /** 权限名称 */
    private String name;
    /** 权限表达式 */
    private String expression;
}
