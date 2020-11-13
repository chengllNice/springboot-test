package com.chenglulu.controller.users.domain;

import lombok.Data;

import java.util.List;

@Data
public class FindUsersParams {

    private String teamId;

    private String userId;

    private String username;

    private String phone;

    private String email;

    private Byte delete;

    private List<String> statusList;

}
