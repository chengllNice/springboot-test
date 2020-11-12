package com.chenglulu.mybatis.dao;

import com.chenglulu.mybatis.entity.OperationRecord;
import com.chenglulu.mybatis.entity.OperationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationRecordMapper {
    long countByExample(OperationRecordExample example);

    int deleteByExample(OperationRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(OperationRecord record);

    int insertSelective(OperationRecord record);

    List<OperationRecord> selectByExampleWithBLOBs(OperationRecordExample example);

    List<OperationRecord> selectByExample(OperationRecordExample example);

    OperationRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByExample(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByPrimaryKeySelective(OperationRecord record);

    int updateByPrimaryKeyWithBLOBs(OperationRecord record);

    int updateByPrimaryKey(OperationRecord record);
}