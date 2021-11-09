package com.jsh.shiro.service;

import com.jsh.shiro.entity.RolePermission;
import com.jsh.shiro.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IRolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    
    public List<RolePermission> selectAll() {
        return rolePermissionMapper.selectAll();
    }

    
    public List<RolePermission> selectPermissionByRoleId(Long id) {
        return rolePermissionMapper.selectPermissionByRoleId(id);
    }

    
    public List<RolePermission> selectRoleByPermissionId(Long id) {
        return rolePermissionMapper.selectRoleByPermissionId(id);
    }

    
    public void deleteByRoleId(Long roleId) {
        rolePermissionMapper.deleteByRoleId(roleId);
    }
}
