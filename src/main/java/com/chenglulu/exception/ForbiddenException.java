package com.chenglulu.exception;

public class ForbiddenException extends BaseException {
    private static final long serialVersionUID = -8842039437278766316L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String code) {
        super(code);
    }

    public ForbiddenException(String code, Object... msgArgs) {
        super(code, msgArgs);
    }
}
