package com.chenglulu.service.users;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class UsersConfig {
    @Value("${user.login.max.limit}")
    private String userLoginMaxLimit;
}
