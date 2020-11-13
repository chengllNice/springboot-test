package com.chenglulu.service.database;

import com.chenglulu.enums.UserDeleteEnum;
import com.chenglulu.enums.UserStatusEnum;
import com.chenglulu.mybatis.dao.UserTokenMapper;
import com.chenglulu.mybatis.entity.UserToken;
import com.chenglulu.mybatis.entity.UserTokenExample;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.mybatis.entity.UsersExample;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserTokenDatabase {
    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 新建用户登录token
     * @param userId userId
     * @param token token
     */
    public UserToken insertUserToken(String userId, String token){
        UserToken userToken = new UserToken();
        Date date = new Date();

        userToken.setId(CommonUtils.getUuid());
        userToken.setUserId(userId);
        userToken.setToken(token);
        userToken.setCreateTime(date);
        int insertResult = userTokenMapper.insertSelective(userToken);
        log.info("insertUserToken insertResult = {}", insertResult);

        if(insertResult == 1){
            return userToken;
        }
        return null;
    }

    /**
     * 删除用户登录token
     * @param userId userId
     */
    public void deleteUserTokenByUserId(String userId){
        UserTokenExample example = new UserTokenExample();
        UserTokenExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(userId);

        int result = userTokenMapper.deleteByExample(example);
        log.info("deleteUserTokenByUserId result = {}", result);
    }
}
