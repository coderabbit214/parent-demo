package com.jsh.shiro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.Permission;
import com.jsh.shiro.mapper.PermissionMapper;
import com.jsh.shiro.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:29 下午
 */
@Service
public class IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }


    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }


    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }


    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }


    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    
    public PageInfo<Permission> list(QueryObject qo) {
        //我们通过PageHelper进行设置分页及排序
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询中不再使用limit
        List<Permission> list = permissionMapper.selectForList(qo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    
    public List<Permission> selectByUserId(Long id) {
        return permissionMapper.selectByUserId(id);
    }

    
    public List<Permission> selectByRoleId(Long id) {
        return permissionMapper.selectByRoleId(id);
    }

    
    public List<String> getPermissionExpressionByUserId(Long id) {
        return permissionMapper.getPermissionExpressionByUserId(id);
    }
}
