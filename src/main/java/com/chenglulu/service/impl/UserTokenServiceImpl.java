package com.chenglulu.service.impl;

import com.chenglulu.mybatis.entity.UserToken;
import com.chenglulu.service.database.UserTokenDatabase;
import com.chenglulu.service.UserTokenService;
import com.chenglulu.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDatabase userTokenDatabase;

    private static final Logger logger = LoggerFactory.getLogger(UserTokenServiceImpl.class);

    /**
     * 新建用户登录token
     * @param userId userId
     * @return Users
     */
    @Override
    public UserToken createUserToken(String username, String userId, boolean isRememberMe){

        // 先删除对应的token
        deleteUserToken(userId);

        // 生成token
        String token = JwtTokenUtils.createToken(username, userId, isRememberMe);
        return userTokenDatabase.insertUserToken(userId, token);
    }

    /**
     * 删除用户token
     * @param userId userId
     */
    @Override
    public void deleteUserToken(String userId){
        userTokenDatabase.deleteUserTokenByUserId(userId);
    }
}
