package com.chenglulu.mybatis.dao;

import com.chenglulu.mybatis.entity.LoginRecord;
import com.chenglulu.mybatis.entity.LoginRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginRecordMapper {
    long countByExample(LoginRecordExample example);

    int deleteByExample(LoginRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    List<LoginRecord> selectByExample(LoginRecordExample example);

    LoginRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByExample(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}