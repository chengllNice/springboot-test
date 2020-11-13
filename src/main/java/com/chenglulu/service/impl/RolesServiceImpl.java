package com.chenglulu.service.impl;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.roles.domain.FindRolesParams;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.service.database.RolesDatabase;
import com.chenglulu.service.RolesService;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesDatabase rolesDatabase;

    private static final Logger logger = LoggerFactory.getLogger(RolesServiceImpl.class);

    /**
     * 新建用户时，验证角色属于当前team并且不是超级管理员
     * @param teamId teamId
     * @param roleId roleId
     */
    public void validTeamRoleForCreateUser(String teamId, String roleId){
        Roles roles = rolesDatabase.findRolesById(roleId);

        // 验证用户的角色是否存在
        if(roles == null){
            throw new RequestException(ErrorCode.USER_ROLE_NOT_EXIST);
        }

        // 验证用户的角色是否存在于本团队中
        if(!roles.getTeamId().equals(teamId)){
            throw new RequestException(ErrorCode.USER_ROLE_NOT_EXIST);
        }

        // 用户不能是超级管理员
        if(roles.getKey().equals(Constants.DEFAULT_ROLE_KEY)){
            throw new RequestException(ErrorCode.USER_ROLE_NOT_EXIST);
        }
    }

    /**
     * 添加角色
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public Roles createRoles(ApiAuth auth, CreateRolesParams params){

        String teamId = auth.getTeamId();

        Roles roles = rolesDatabase.findRolesById(params.getRoleId());

        if(roles != null){
            throw new RequestException(ErrorCode.ROLES_EXIST);
        }

        Roles newRoles = new Roles();
        newRoles.setKey(params.getKey());
        newRoles.setName(params.getName());
        newRoles.setTeamId(auth.getTeamId());
        return rolesDatabase.insertRoles(newRoles);
    }


    /**
     * 角色列表
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户列表
     */
    @Override
    public List<Roles> queryRolesList(ApiAuth auth, FindRolesParams params){
        return rolesDatabase.findRoles(auth.getTeamId(), params.getName());
    }
}
