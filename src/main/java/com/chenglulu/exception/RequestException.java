package com.chenglulu.exception;

public class RequestException extends BaseException {
    private static final long serialVersionUID = -8842039437278766316L;

    public RequestException() {
        super();
    }

    public RequestException(String code) {
        super(code);
    }

    public RequestException(String code, Object... msgArgs) {
        super(code, msgArgs);
    }
}
