package com.jsh.shiro.mapper;

import com.jsh.shiro.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    int insert(RolePermission record);
    List<RolePermission> selectAll();
    List<RolePermission> selectPermissionByRoleId(Long id);
    List<RolePermission> selectRoleByPermissionId(Long id);

    void deleteByRoleId(Long roleId);
}