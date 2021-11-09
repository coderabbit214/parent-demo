package com.jsh.shiro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.User;
import com.jsh.shiro.mapper.UserMapper;
import com.jsh.shiro.util.QueryObject;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:28 下午
 */
@Service
public class IUserService {
    @Autowired
    private UserMapper UserMapper;

    
    public int deleteByPrimaryKey(Long id) {

        return UserMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(User record) {
        if (record.getPassword() != null) {
            Md5Hash md5Hash = new Md5Hash(record.getPassword(), record.getName());
            record.setPassword(md5Hash.toString());
        }
        return UserMapper.insert(record);
    }

    
    public User selectByPrimaryKey(Long id) {
        return UserMapper.selectByPrimaryKey(id);
    }

    
    public List<User> selectAll() {
        return UserMapper.selectAll();
    }

    
    public int updateByPrimaryKey(User record) {
        return UserMapper.updateByPrimaryKey(record);
    }

    
    public PageInfo<User> list(QueryObject qo) {
        //我们通过PageHelper进行设置分页及排序
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), "id asc");
        //查询中不再使用limit
        List<User> list = UserMapper.selectForList(qo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    
    public void batchDeleteByIds(Long[] ids) {
        UserMapper.batchDeleteByIds(ids);
    }

    
    public User selectByUsername(String username) {
        return UserMapper.selectByUsername(username);
    }

    
    public void batchdelete(Long[] ids) {
        UserMapper.batchDeleteByIds(ids);
    }
    
}
