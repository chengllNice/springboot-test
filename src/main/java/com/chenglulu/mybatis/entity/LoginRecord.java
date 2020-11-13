package com.chenglulu.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginRecord implements Serializable {
    private String id;

    private String userId;

    private String ip;

    private String place;

    private String equipment;

    private String equipmentCode;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public LoginRecord(String id, String userId, String ip, String place, String equipment, String equipmentCode, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.ip = ip;
        this.place = place;
        this.equipment = equipment;
        this.equipmentCode = equipmentCode;
        this.createTime = createTime;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode == null ? null : equipmentCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", ip=").append(ip);
        sb.append(", place=").append(place);
        sb.append(", equipment=").append(equipment);
        sb.append(", equipmentCode=").append(equipmentCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}