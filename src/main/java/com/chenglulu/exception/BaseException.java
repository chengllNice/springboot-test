package com.chenglulu.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 4941861261481196240L;

    private String code;
    private Object[] msgArgs;

    public BaseException() {
        super();
    }

    public BaseException(String code) {
        this.code = code;
    }

    public BaseException(String code, Object... msgArgs) {
        this.code = code;
        this.msgArgs = msgArgs;
    }

    public String getCode() {
        return code;
    }

    public Object[] getMsgArgs() {
        return msgArgs;
    }

    public void setMsgArgs(Object[] msgArgs) {
        this.msgArgs = msgArgs;
    }
}
