package com.chenglulu.service.users.database;

import com.chenglulu.controller.users.domain.FindUsersParams;
import com.chenglulu.enums.UserDeleteEnum;
import com.chenglulu.enums.UserStatusEnum;
import com.chenglulu.mybatis.dao.UsersMapper;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.mybatis.entity.UsersExample;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DatabaseUsers {
    @Autowired(required=false)
    private UsersMapper usersMapper;

    /**
     * 新建用户
     * @param params RegisterUsersParams
     * @return boolean
     */
    public Users insertUser(Users params){
        Users users = new Users();
        Date date = new Date();

        users.setId(CommonUtils.getUuid());
        users.setUserName(params.getUserName());
        users.setPassword(params.getPassword());
        users.setRealName(params.getRealName());
        users.setEmail(params.getEmail());
        users.setPhone(params.getPhone());
        users.setRoleId(params.getRoleId());
        users.setTeamId(params.getTeamId());
        users.setCreateTime(date);
        users.setUpdateTime(date);
        users.setStatus(UserStatusEnum.OK.getCode());
        users.setDelete(UserDeleteEnum.NORMAL.getCode());
        int insertResult = usersMapper.insertSelective(users);
        log.info("insertUser insertResult = {}", insertResult);
        if(insertResult == 1){
            return users;
        }
        return null;
    }


    /**
     * 查询用户列表
     * @param params FindUsersParams
     * @return List<Users>
     */
    public List<Users> findUsers(FindUsersParams params){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        // 按照创建时间 降序排列, id 升序排列； ASC升序，DESC降序，多个条件用逗号分隔
        example.setOrderByClause("create_time desc, id asc");

        if(StringUtils.isNotEmpty(params.getId())){
            criteria.andIdEqualTo(params.getId());
        }

        // 用户名模糊查询
        if(StringUtils.isNotEmpty(params.getUsername())){
            criteria.andUserNameLike("%" + params.getUsername() + "%");
        }

        // 手机号查询
        if(StringUtils.isNotEmpty(params.getPhone())){
            criteria.andPhoneEqualTo(params.getPhone());
        }

        // 邮箱模糊查询
        if(StringUtils.isNotEmpty(params.getEmail())){
            criteria.andEmailEqualTo("%" + params.getEmail() + "%");
        }

        // 删除状态查询
        if(params.getDelete() != null){
            criteria.andDeleteEqualTo(params.getDelete());
        }

        // 多状态过滤
        if(CollectionUtils.isNotEmpty(params.getStatusList())){
            criteria.andStatusIn(params.getStatusList());
        }

        List<Users> usersList = usersMapper.selectByExample(example);
        log.info("findUsers usersList = {}", usersList);
        return usersList;
    }


    /**
     * 查询指定用户信息（正常、未删除的）
     * @param userId 用户ID
     * @return List<Users>
     */
    public Users findUserStatusOkById(String userId){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(userId);
        criteria.andDeleteEqualTo(UserDeleteEnum.NORMAL.getCode());
        criteria.andStatusEqualTo(UserStatusEnum.OK.getCode());

        List<Users> usersList = usersMapper.selectByExample(example);
        log.info("findUserStatusOkById usersList = {}", usersList);

        if(CollectionUtils.isNotEmpty(usersList)){
            return usersList.get(0);
        }
        return null;
    }


    /**
     * 登录
     * @param email 用户email
     * @param phone 用户phone
     * @param password 用户password
     * @return Users
     */
    public Users findUserForLogin(String email, String phone, String password){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(email)){
            criteria.andEmailEqualTo(email);
        }
        if(StringUtils.isNotBlank(phone)){
            criteria.andPhoneEqualTo(phone);
        }
        criteria.andPasswordEqualTo(password);
        criteria.andDeleteEqualTo(UserDeleteEnum.NORMAL.getCode());
        criteria.andStatusEqualTo(UserStatusEnum.OK.getCode());

        List<Users> usersList = usersMapper.selectByExample(example);
        log.info("findUserStatusOkById usersList = {}", usersList);

        if(usersList.size() == 1){
            return usersList.get(0);
        }
        return null;
    }


    /**
     * 删除指定用户
     * @param userId 用户ID
     */
    public void deleteUserById(String userId){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(userId);

        int result = usersMapper.deleteByExample(example);
        log.info("deleteUserById result = {}", result);
    }
}
