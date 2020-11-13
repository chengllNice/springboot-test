package com.chenglulu.exception;


public class ServiceException extends BaseException {
    private static final long serialVersionUID = 7230455365497726442L;

    public ServiceException() {
        super();
    }

    public ServiceException(String code) {
        super(code);
    }

    public ServiceException(String code, Object... msgArgs) {
        super(code, msgArgs);
    }
}
