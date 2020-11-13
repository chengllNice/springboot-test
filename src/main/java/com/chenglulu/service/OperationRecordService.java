package com.chenglulu.service;

import com.chenglulu.controller.operation_record.domain.CreateOperationRecordParams;
import com.chenglulu.mybatis.entity.OperationRecord;
import com.chenglulu.service.database.OperationRecordDatabase;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface OperationRecordService {

    /**
     * 添加操作记录
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    OperationRecord createOperationRecord(ApiAuth auth, CreateOperationRecordParams params);
}
