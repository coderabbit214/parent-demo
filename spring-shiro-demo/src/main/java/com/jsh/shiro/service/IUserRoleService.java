package com.jsh.shiro.service;

import com.jsh.shiro.entity.UserRole;
import com.jsh.shiro.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserRoleService  {

    @Autowired
    private UserRoleMapper userRoleMapper;

    
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }


    
    public List<UserRole> selectAll() {
        return userRoleMapper.selectAll();
    }

    
    public void deleteByUserId(Long id) {
        userRoleMapper.deleteByUserId(id);
    }

    
    public void batchDeleteByUserIds(Long[] ids) {
        userRoleMapper.batchDeleteByUserIds(ids);
    }


}
