package com.chenglulu.constant;

public class Constants {

    public static final String Authorization = "Authorization";
    public static final String AuthorizationPrefix = "Bearer ";

    // header信息
    public static final String HEADER_X_REQUEST_ID = "X-REQUEST-ID";
    public static final String HEADER_X_USER_ID = "X-USER-ID";


    /*================= request信息 =================*/
    public static final String REQUEST_START_TIME = "REQUEST-START-TIME";
    public static final String REQUEST_USER_INFO = "REQUEST-USER-INFO";
    public static final String REQUEST_USER_TOKEN = "REQUEST-USER-TOKEN";


    /*================= token 信息 =================*/
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    // 私钥
    public static final String TOKEN_SECRET = "maven.chenglulu.com";
    // 该JWT的签发者
    public static final String TOKEN_ISS = "chenglulu.com";
    // 过期时间是3600秒，既是1个小时
    public static final long TOKEN_EXPIRATION = 3600L;
    // 选择了记住我之后的过期时间为7天
    public static final long TOKEN_EXPIRATION_REMEMBER = 604800L;

    /*================= role 信息 =================*/
    public static final String DEFAULT_ROLE_KEY = "super_administrator";
    public static final String DEFAULT_ROLE_NAME = "超级管理员";
}
