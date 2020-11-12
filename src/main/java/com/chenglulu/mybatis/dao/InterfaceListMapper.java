package com.chenglulu.mybatis.dao;

import com.chenglulu.mybatis.entity.InterfaceList;
import com.chenglulu.mybatis.entity.InterfaceListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterfaceListMapper {
    long countByExample(InterfaceListExample example);

    int deleteByExample(InterfaceListExample example);

    int deleteByPrimaryKey(@Param("id") String id, @Param("key") String key);

    int insert(InterfaceList record);

    int insertSelective(InterfaceList record);

    List<InterfaceList> selectByExample(InterfaceListExample example);

    InterfaceList selectByPrimaryKey(@Param("id") String id, @Param("key") String key);

    int updateByExampleSelective(@Param("record") InterfaceList record, @Param("example") InterfaceListExample example);

    int updateByExample(@Param("record") InterfaceList record, @Param("example") InterfaceListExample example);

    int updateByPrimaryKeySelective(InterfaceList record);

    int updateByPrimaryKey(InterfaceList record);
}