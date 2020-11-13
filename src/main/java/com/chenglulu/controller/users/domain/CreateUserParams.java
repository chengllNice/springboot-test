package com.chenglulu.controller.users.domain;

import com.chenglulu.constant.Validation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserParams {

    @NotBlank
    @Size(min = 6, max = 32)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String realName;

    @NotBlank
    @Pattern(regexp = Validation.PHONE)
    private String phone;

    @NotBlank
    @Pattern(regexp = Validation.EMAIL)
    private String email;

    @NotBlank
    @Pattern(regexp = Validation.UUID)
    private String roleId;
}
