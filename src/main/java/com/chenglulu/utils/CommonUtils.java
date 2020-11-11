package com.chenglulu.utils;

import java.util.Date;
import java.util.UUID;

public class CommonUtils {
    public static String getUuid(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args){
        Date d = new Date();
        System.out.println();
    }
}
