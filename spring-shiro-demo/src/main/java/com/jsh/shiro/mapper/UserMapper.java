package com.jsh.shiro.mapper;

import com.jsh.shiro.entity.User;
import com.jsh.shiro.util.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 4:24 下午
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectForList(QueryObject qo);

    void batchDeleteByIds(Long[] ids);

    User selectByUsername(String username);
}
