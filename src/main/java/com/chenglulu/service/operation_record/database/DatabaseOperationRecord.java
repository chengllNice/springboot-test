package com.chenglulu.service.operation_record.database;

import com.chenglulu.mybatis.dao.OperationRecordMapper;
import com.chenglulu.mybatis.entity.OperationRecord;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class DatabaseOperationRecord {
    @Autowired(required=false)
    private OperationRecordMapper operationRecordMapper;

    /**
     * 新建操作记录
     * @param params RegisterUsersParams
     * @return boolean
     */
    public OperationRecord insertOperationRecord(OperationRecord params){
        OperationRecord operationRecord = new OperationRecord();
        Date date = new Date();

        operationRecord.setId(CommonUtils.getUuid());
        operationRecord.setInterfaceId(params.getInterfaceId());
        operationRecord.setUserId(params.getUserId());
        operationRecord.setStatus(params.getStatus());
        operationRecord.setReason(params.getReason());
        operationRecord.setCreateTime(date);
        int insertResult = operationRecordMapper.insertSelective(operationRecord);
        log.info("insertOperationRecord insertResult = {}", insertResult);
        if(insertResult == 1){
            return operationRecord;
        }
        return null;
    }
}
