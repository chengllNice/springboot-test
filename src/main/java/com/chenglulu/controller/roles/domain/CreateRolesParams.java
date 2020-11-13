package com.chenglulu.controller.roles.domain;

import com.chenglulu.constant.Validation;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateRolesParams {

    @NotBlank
    @Pattern(regexp = Validation.UUID)
    private String roleId;

    @NotBlank
    @Max(value = 20)
    private String name;
}
