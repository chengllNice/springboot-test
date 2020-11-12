package com.chenglulu.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class OperationRecord implements Serializable {
    private String id;

    private String userId;

    private String interfaceId;

    private String status;

    private Date createTime;

    private String reason;

    private static final long serialVersionUID = 1L;

    public OperationRecord(String id, String userId, String interfaceId, String status, Date createTime, String reason) {
        this.id = id;
        this.userId = userId;
        this.interfaceId = interfaceId;
        this.status = status;
        this.createTime = createTime;
        this.reason = reason;
    }

    public OperationRecord() {
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

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId == null ? null : interfaceId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", interfaceId=").append(interfaceId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", reason=").append(reason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}