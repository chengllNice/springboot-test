package com.chenglulu.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class PermissionTemplate implements Serializable {
    private String id;

    private String roleId;

    private String interfaceId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public PermissionTemplate(String id, String roleId, String interfaceId, Date createTime) {
        this.id = id;
        this.roleId = roleId;
        this.interfaceId = interfaceId;
        this.createTime = createTime;
    }

    public PermissionTemplate() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId == null ? null : interfaceId.trim();
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
        sb.append(", roleId=").append(roleId);
        sb.append(", interfaceId=").append(interfaceId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}