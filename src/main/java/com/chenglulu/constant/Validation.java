package com.chenglulu.constant;


public interface Validation {
    // UUID
    String UUID = "[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}";

    //邮箱
    String EMAIL = "^[A-Za-z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    //手机
    String PHONE = "^1(3|4|5|6|7|8|9)\\d{9}$";

    // status状态
    String STATUS = "^[ok|error|delete|blocked|disable]$";
}
