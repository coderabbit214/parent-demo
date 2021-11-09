package com.jsh.shiro.mapper;

import com.jsh.shiro.entity.Permission;
import com.jsh.shiro.util.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/11/8 4:08 下午
 */
@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectForList(QueryObject qo);

    List<Permission> selectByUserId(Long id);

    List<Permission> selectByRoleId(Long id);

    List<String> getPermissionExpressionByUserId(Long id);
}
