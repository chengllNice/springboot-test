package com.chenglulu.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum UserRoleEnum {
    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "管理员"),
    NORMAL(10, "普通用户"),
    ;

    private final Integer code;
    private final String name;

    UserRoleEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public List<UserRoleEnum> getRoleList(){
        List<UserRoleEnum> result = new ArrayList<UserRoleEnum>();
        result.addAll(Arrays.asList(UserRoleEnum.values()));
        return result;
    }

    public String getName(){
        return name;
    }

    public Integer getCode(){
        return code;
    }
}
