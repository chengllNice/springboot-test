package com.chenglulu.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum UserStatusEnum {
    OK("ok", "正常"),
    ERROR("error", "错误"),
    DELETE("delete", "已删除"),
    BLOCKED("blocked", "冻结"),
    DISABLE("disable", "禁用"),
    ;

    private final String code;
    private final String name;

    UserStatusEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }
}
