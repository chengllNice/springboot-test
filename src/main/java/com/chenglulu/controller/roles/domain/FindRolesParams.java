package com.chenglulu.controller.roles.domain;

import com.chenglulu.constant.Validation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class FindRolesParams {
    private String name;// 模糊查询
}
