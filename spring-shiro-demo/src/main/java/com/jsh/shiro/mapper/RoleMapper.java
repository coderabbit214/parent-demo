package com.jsh.shiro.mapper;

import com.jsh.shiro.entity.Role;
import com.jsh.shiro.util.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 3:56 下午
 */
@Mapper
public interface RoleMapper {
    List<String> getRoleSnByUserId(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(QueryObject qo);

    List<Role> selectByUserId(Long id);

}
