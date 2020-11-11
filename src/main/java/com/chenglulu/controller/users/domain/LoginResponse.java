package com.chenglulu.controller.users.domain;

import com.chenglulu.mybatis.entity.Users;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
}
