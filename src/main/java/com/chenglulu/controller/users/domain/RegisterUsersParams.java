package com.chenglulu.controller.users.domain;

import com.chenglulu.constant.Validation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterUsersParams {

//    @NotBlank(message = "i18n.not.blank")
//    @Size(min = 6, max = 32, message = "i18n.size")
//    private String username;
//
//    @NotBlank(message = "i18n.not.blank")
//    private String password;
//
//    @NotBlank(message = "i18n.not.blank")
//    private String realName;
//
//    @NotBlank(message = "i18n.not.blank")
//    @Pattern(regexp = Validation.PHONE, message = "i18n.pattern")
//    private String phone;
//
//    @NotBlank(message = "i18n.not.blank")
//    @Pattern(regexp = Validation.EMAIL, message = "i18n.pattern")
//    private String email;

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
    private String teamId;
}
