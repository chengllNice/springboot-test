package com.chenglulu.mybatis.dao;

import com.chenglulu.mybatis.entity.PermissionTemplate;
import com.chenglulu.mybatis.entity.PermissionTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionTemplateMapper {
    long countByExample(PermissionTemplateExample example);

    int deleteByExample(PermissionTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(PermissionTemplate record);

    int insertSelective(PermissionTemplate record);

    List<PermissionTemplate> selectByExample(PermissionTemplateExample example);

    PermissionTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PermissionTemplate record, @Param("example") PermissionTemplateExample example);

    int updateByExample(@Param("record") PermissionTemplate record, @Param("example") PermissionTemplateExample example);

    int updateByPrimaryKeySelective(PermissionTemplate record);

    int updateByPrimaryKey(PermissionTemplate record);
}