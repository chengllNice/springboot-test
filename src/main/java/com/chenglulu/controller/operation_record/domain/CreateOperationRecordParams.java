package com.chenglulu.controller.operation_record.domain;

import lombok.Data;

@Data
public class CreateOperationRecordParams {

    private String userId;

    private String interfaceId;

    private String reason;

    private String status;
}
