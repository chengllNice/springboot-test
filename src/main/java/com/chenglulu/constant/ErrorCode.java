package com.chenglulu.constant;

public interface ErrorCode {
    String UNKNOWN_ERROR="UNKNOWN_ERROR";
    String SUCCESS="SUCCESS";
    String USER_NOT_EXIST="USER_NOT_EXIST";
    String USER_IS_EXISTS="USER_IS_EXISTS";
    String PARAMETER_ERROR="PARAMETER_ERROR";


    String LOGIN_ERROR="LOGIN_ERROR";
    String LOGIN_Limit_ERROR="LOGIN_Limit_ERROR";


    // Authorization不存在或者格式不正确
    String AUTHORIZATION_INCORRECT="AUTHORIZATION_INCORRECT";
    String AUTHORIZATION_INVALID="AUTHORIZATION_INVALID";
}
