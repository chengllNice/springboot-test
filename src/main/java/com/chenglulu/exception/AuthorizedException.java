package com.chenglulu.exception;

public class AuthorizedException extends BaseException {
    private static final long serialVersionUID = -8842039437278766316L;

    public AuthorizedException() {
        super();
    }

    public AuthorizedException(String code) {
        super(code);
    }

    public AuthorizedException(String code, Object... msgArgs) {
        super(code, msgArgs);
    }
}
