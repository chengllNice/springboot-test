package com.chenglulu.mybatis.dao;

import com.chenglulu.mybatis.entity.PermissionUser;
import com.chenglulu.mybatis.entity.PermissionUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionUserMapper {
    long countByExample(PermissionUserExample example);

    int deleteByExample(PermissionUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PermissionUser record);

    int insertSelective(PermissionUser record);

    List<PermissionUser> selectByExample(PermissionUserExample example);

    PermissionUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PermissionUser record, @Param("example") PermissionUserExample example);

    int updateByExample(@Param("record") PermissionUser record, @Param("example") PermissionUserExample example);

    int updateByPrimaryKeySelective(PermissionUser record);

    int updateByPrimaryKey(PermissionUser record);
}