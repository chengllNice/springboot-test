package com.chenglulu.controller.users.domain;

import lombok.Data;

import java.util.Date;

@Data
public class QueryUsersResponse {
    private String id;

    private String userName;

    private String realName;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private String email;

    private String status;

    private String statusName;

    private Byte delete;

    private Byte deleteName;

    private String roleId;

    private String roleName;

    private String teamId;
}
