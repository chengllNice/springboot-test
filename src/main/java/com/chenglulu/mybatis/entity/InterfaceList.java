package com.chenglulu.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class InterfaceList implements Serializable {
    private String id;

    private String key;

    private String name;

    private String description;

    private String method;

    private String interfaceAddress;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public InterfaceList(String id, String key, String name, String description, String method, String interfaceAddress, Date createTime) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.description = description;
        this.method = method;
        this.interfaceAddress = interfaceAddress;
        this.createTime = createTime;
    }

    public InterfaceList() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getInterfaceAddress() {
        return interfaceAddress;
    }

    public void setInterfaceAddress(String interfaceAddress) {
        this.interfaceAddress = interfaceAddress == null ? null : interfaceAddress.trim();
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
        sb.append(", key=").append(key);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", method=").append(method);
        sb.append(", interfaceAddress=").append(interfaceAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}