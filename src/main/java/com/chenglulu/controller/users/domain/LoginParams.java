package com.chenglulu.controller.users.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginParams {

    @NotNull
    @NotBlank
    private String account;

    @NotNull
    @NotBlank
    private String password;

    private Boolean isRememberMe = false;
}
