package com.chenglulu.service.impl;

import com.chenglulu.controller.operation_record.domain.CreateOperationRecordParams;
import com.chenglulu.mybatis.entity.OperationRecord;
import com.chenglulu.service.database.OperationRecordDatabase;
import com.chenglulu.service.OperationRecordService;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationRecordServiceImpl implements OperationRecordService {
    @Autowired
    private OperationRecordDatabase operationRecordDatabase;

    private static final Logger logger = LoggerFactory.getLogger(OperationRecordService.class);

    /**
     * 添加操作记录
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public OperationRecord createOperationRecord(ApiAuth auth, CreateOperationRecordParams params){

        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setReason(params.getReason());
        operationRecord.setStatus(params.getStatus());
        operationRecord.setInterfaceId(params.getInterfaceId());

        if(StringUtils.isBlank(params.getUserId())){
            operationRecord.setUserId(auth.getUserId());
        }else {
            operationRecord.setUserId(params.getUserId());
        }

        return operationRecordDatabase.insertOperationRecord(operationRecord);
    }
}
