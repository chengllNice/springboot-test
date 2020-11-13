package com.chenglulu.service;

import com.chenglulu.controller.users.domain.*;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsersService {

    /**
     * 注册
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    Users registerUser(ApiAuth auth, RegisterUsersParams params);

    /**
     * 添加账号
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    Users createUser(ApiAuth auth, CreateUserParams params);

    /**
     * 登录
     * @param params 查询参数
     * @return 用户信息
     */
    LoginResponse login(LoginParams params);

    /**
     * 查询用户列表
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户列表
     */
    List<Users> queryUserList(ApiAuth auth, QueryUsersListParams params);

    /**
     * 通过ID查询指定用户
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户信息
     */
    Users queryUserById(ApiAuth auth, QueryUserByIdParams params);
}
