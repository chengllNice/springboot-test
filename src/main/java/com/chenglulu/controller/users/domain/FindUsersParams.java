package com.chenglulu.controller.users.domain;

import lombok.Data;

import java.util.List;

@Data
public class FindUsersParams {

    private String id;

    private String username;

    private String phone;

    private String email;

    private Byte delete = 0;

    private List<String> statusList;

}
