package com.chenglulu.service.roles.database;

import com.chenglulu.mybatis.dao.RolesMapper;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.mybatis.entity.RolesExample;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DatabaseRoles {
    @Autowired(required=false)
    private RolesMapper rolesMapper;

    /**
     * 新建角色
     * @param params RegisterUsersParams
     * @return boolean
     */
    public Roles insertRoles(Roles params){
        Roles roles = new Roles();
        Date date = new Date();

        roles.setId(CommonUtils.getUuid());
        roles.setKey(params.getKey());
        roles.setName(params.getName());
        roles.setTeamId(params.getTeamId());
        roles.setCreateTime(date);
        roles.setUpdateTime(date);
        int insertResult = rolesMapper.insertSelective(roles);
        log.info("insertRoles insertResult = {}", insertResult);
        if(insertResult == 1){
            return roles;
        }
        return null;
    }


    /**
     * 修改角色
     * @param params RegisterUsersParams
     * @return boolean
     */
    public Roles updateRoles(Roles params){
        Roles roles = new Roles();
        Date date = new Date();

        roles.setId(CommonUtils.getUuid());
        roles.setKey(params.getKey());
        roles.setName(params.getName());
        roles.setTeamId(params.getTeamId());
        roles.setCreateTime(date);
        roles.setUpdateTime(date);
        int insertResult = rolesMapper.insertSelective(roles);
        log.info("insertRoles insertResult = {}", insertResult);
        if(insertResult == 1){
            return roles;
        }
        return null;
    }


    /**
     * 查询角色列表
     * @return List<Roles>
     */
    public List<Roles> findRoles(String teamId,  String name){
        RolesExample example = new RolesExample();
        RolesExample.Criteria criteria = example.createCriteria();

        // 按照创建时间 降序排列, id 升序排列； ASC升序，DESC降序，多个条件用逗号分隔
        example.setOrderByClause("create_time desc, id asc");

        criteria.andTeamIdEqualTo(teamId);

        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike("%" + name + "%");
        }

        List<Roles> rolesList = rolesMapper.selectByExample(example);
        log.info("findRoles rolesList = {}", rolesList);
        return rolesList;
    }


    /**
     * 查询角色通过KEY
     * @return Roles
     */
    public Roles findRolesByKey(String teamId,  String key){
        RolesExample example = new RolesExample();
        RolesExample.Criteria criteria = example.createCriteria();

        criteria.andTeamIdEqualTo(teamId);
        criteria.andKeyEqualTo(key);

        List<Roles> rolesList = rolesMapper.selectByExample(example);
        log.info("findRolesByKey rolesList = {}", rolesList);

        if(rolesList.size() > 0){
            return rolesList.get(0);
        }
        return null;
    }
}
