package com.chenglulu.service;

import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.roles.domain.FindRolesParams;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.service.database.RolesDatabase;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RolesService {

    /**
     * 添加角色
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    Roles createRoles(ApiAuth auth, CreateRolesParams params);


    /**
     * 角色列表
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户列表
     */
    List<Roles> queryRolesList(ApiAuth auth, FindRolesParams params);


    /**
     * 角色根据key
     * @param teamId teamId
     * @param key key
     * @return 用户列表
     */
    Roles queryUserRolesByKey(String teamId, String key);
}
