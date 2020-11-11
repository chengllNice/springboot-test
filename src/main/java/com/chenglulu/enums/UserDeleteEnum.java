package com.chenglulu.enums;

public enum UserDeleteEnum {
    DELETE(new Byte("1"), "已删除"),
    NORMAL(new Byte("0"), "正常"),
    ;

    private final Byte code;
    private final String name;

    UserDeleteEnum(Byte code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Byte getCode(){
        return code;
    }
}
