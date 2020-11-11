package com.chenglulu.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiAuth {
    private String requestId;
    private String requestTime;
    private String userId;
    private String token;
    private String realIp;
    private String equipment;

    public ApiAuth(String requestId, String userId, String token){
        this.requestId = requestId;
        this.userId = userId;
        this.token = token;
    }


    public String getRequestId(){
        return requestId;
    }

    public void setRequestId(String requestId){
        this.requestId = requestId;
    }

    public String getRequestTime(){
        return requestTime;
    }

    public void setRequestTime(String requestTime){
        this.requestTime = requestTime;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getRealIp(){
        return realIp;
    }

    public void setRealIp(String realIp){
        this.realIp = realIp;
    }

    public String getEquipment(){
        return equipment;
    }

    public void setEquipment(String equipment){
        this.equipment = equipment;
    }
}
