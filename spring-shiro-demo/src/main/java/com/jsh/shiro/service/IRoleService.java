package com.jsh.shiro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.Role;
import com.jsh.shiro.mapper.RoleMapper;
import com.jsh.shiro.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:28 下午
 */
@Service
public class IRoleService {
    
    @Autowired
    RoleMapper roleMapper;

    public List<String> getRoleSnByUserId(Long id) {
        return roleMapper.getRoleSnByUserId(id);
    }


    
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    
    public PageInfo<Role> list(QueryObject qo) {
        //我们通过PageHelper进行设置分页及排序
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        //查询中不再使用limit
        List<Role> list = roleMapper.selectForList(qo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public List<Role> selectByUserId(Long id) {
        return roleMapper.selectByUserId(id);
    }

    
}
