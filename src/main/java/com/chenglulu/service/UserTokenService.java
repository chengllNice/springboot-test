package com.chenglulu.service;

import com.chenglulu.mybatis.entity.UserToken;

public interface UserTokenService {

    /**
     * 新建用户登录token
     * @param userId userId
     * @return Users
     */
    UserToken createUserToken(String username, String userId, boolean isRememberMe);

    /**
     * 删除用户token
     * @param userId userId
     */
    void deleteUserToken(String userId);
}
