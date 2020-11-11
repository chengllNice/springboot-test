package com.chenglulu.controller.users.domain;

import com.chenglulu.constant.Validation;
import com.chenglulu.enums.UserStatusEnum;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class QueryUsersListParams {
    @Pattern(regexp = Validation.UUID)
    private String id;

    @Pattern(regexp = Validation.PHONE)
    private String phone;

    @Pattern(regexp = Validation.EMAIL)
    private String email;

    @Pattern(regexp = Validation.STATUS)
    private String status;
}
