package com.jsh.shiro.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolePermission {
    private Long roleId;

    private Long permissionId;
}