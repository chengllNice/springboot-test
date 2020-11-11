package com.chenglulu.controller;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Data;

@Data
public class BaseResponse {
    public BaseResponse() {
        super();
    }


    private String requestId;

    private String message;

    private String code;

    private Object data;

    public BaseResponse(String requestId, String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.requestId = requestId;
    }
}
