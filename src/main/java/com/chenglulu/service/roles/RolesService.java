package com.chenglulu.service.roles;

import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.roles.domain.FindRolesParams;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.service.roles.database.DatabaseRoles;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RolesService {

    @Autowired
    private DatabaseRoles databaseRoles;

    private static final Logger logger = LoggerFactory.getLogger(RolesService.class);


    /**
     * 添加角色
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    public Roles createRoles(ApiAuth auth, CreateRolesParams params){

        Roles roles = queryUserRolesByKey(auth.getTeamId(), params.getKey());

        if(roles != null){
            throw new RequestException(ErrorCode.ROLES_EXIST);
        }

        Roles newRoles = new Roles();
        newRoles.setKey(params.getKey());
        newRoles.setName(params.getName());
        newRoles.setTeamId(auth.getTeamId());
        return databaseRoles.insertRoles(newRoles);
    }


    /**
     * 角色列表
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户列表
     */
    public List<Roles> queryRolesList(ApiAuth auth, FindRolesParams params){
        return databaseRoles.findRoles(auth.getTeamId(), params.getName());
    }


    /**
     * 角色根据key
     * @param teamId teamId
     * @param key key
     * @return 用户列表
     */
    public Roles queryUserRolesByKey(String teamId, String key){
        return databaseRoles.findRolesByKey(teamId, key);
    }
}
