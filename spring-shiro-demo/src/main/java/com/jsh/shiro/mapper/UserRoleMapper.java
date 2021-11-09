package com.jsh.shiro.mapper;

import com.jsh.shiro.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();

    void deleteByUserId(Long id);

    void batchDeleteByUserIds(@Param("ids") Long[] ids);
}