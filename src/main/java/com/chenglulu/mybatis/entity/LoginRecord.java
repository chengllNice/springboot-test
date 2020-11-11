package com.chenglulu.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginRecord implements Serializable {
    private String id;

    private String userId;

    private Date createTime;

    private String place;

    private String equipment;

    private String token;

    private String ip;

    private static final long serialVersionUID = 1L;

    public LoginRecord(String id, String userId, Date createTime, String place, String equipment, String token, String ip) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.place = place;
        this.equipment = equipment;
        this.token = token;
        this.ip = ip;
    }

    public LoginRecord() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", place=").append(place);
        sb.append(", equipment=").append(equipment);
        sb.append(", token=").append(token);
        sb.append(", ip=").append(ip);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}