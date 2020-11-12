package com.chenglulu.controller.users.domain;

import com.chenglulu.constant.Validation;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class QueryUserByIdParams {

    @NotBlank
    @Pattern(regexp = Validation.UUID)
    private String userId;
}
