package com.chenglulu.controller.teams.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTeamsParams {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String address;
}
