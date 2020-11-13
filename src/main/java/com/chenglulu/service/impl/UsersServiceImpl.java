package com.chenglulu.service.impl;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.constant.Validation;
import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.users.domain.*;
import com.chenglulu.enums.UserDeleteEnum;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.entity.LoginRecord;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.mybatis.entity.UserToken;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.service.database.LoginRecordDatabase;
import com.chenglulu.service.database.UsersDatabase;
import com.chenglulu.service.RolesService;
import com.chenglulu.service.UserTokenService;
import com.chenglulu.service.UsersService;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService  {

    @Autowired
    private UsersDatabase usersDatabase;

    @Autowired
    private LoginRecordDatabase loginRecordDatabase;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserTokenService userTokenService;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    /**
     * 验证用户邮箱&手机号
     * @param email email
     * @param phone phone
     */
    private void validUserEmailAndPhone(String email, String phone){

        FindUsersParams findUsersEmailParams = new FindUsersParams();
        findUsersEmailParams.setEmail(email);
        List<Users> usersEmailList = usersDatabase.findUsers(findUsersEmailParams);

        if(CollectionUtils.isNotEmpty(usersEmailList)){
            throw new RequestException(ErrorCode.USER_EMAIL_EXIST);
        }

        FindUsersParams findUsersPhoneParams = new FindUsersParams();
        findUsersPhoneParams.setPhone(email);
        List<Users> usersPhoneList = usersDatabase.findUsers(findUsersPhoneParams);

        if(CollectionUtils.isNotEmpty(usersPhoneList)){
            throw new RequestException(ErrorCode.USER_PHONE_EXIST);
        }
    }



    /**
     * 注册
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public Users registerUser(ApiAuth auth, RegisterUsersParams params){

        validUserEmailAndPhone(params.getEmail(), params.getPhone());

        CreateRolesParams createRolesParams = new CreateRolesParams();
        createRolesParams.setKey(Constants.DEFAULT_ROLE_KEY);
        createRolesParams.setName(Constants.DEFAULT_ROLE_NAME);
        auth.setTeamId(params.getTeamId());
        Roles roles = rolesService.createRoles(auth, createRolesParams);

        String password = bCryptPasswordEncoder.encode(params.getPassword());

        Users users = new Users();
        users.setUserName(params.getUsername());
        users.setPassword(password);
        users.setRealName(params.getRealName());
        users.setEmail(params.getEmail());
        users.setPhone(params.getPhone());
        users.setTeamId(params.getTeamId());
        users.setRoleId(roles.getId());
        return usersDatabase.insertUser(users);
    }

    /**
     * 添加账号
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public Users createUser(ApiAuth auth, CreateUserParams params){

        validUserEmailAndPhone(params.getEmail(), params.getPhone());

        validUserRole(auth.getTeamId(), params.getRoleId());

        String password = bCryptPasswordEncoder.encode(params.getPassword());

        Users users = new Users();
        users.setUserName(params.getUsername());
        users.setPassword(password);
        users.setRealName(params.getRealName());
        users.setEmail(params.getEmail());
        users.setPhone(params.getPhone());
        users.setTeamId(auth.getTeamId());
        users.setRoleId(params.getRole());
        return usersDatabase.insertUser(users);
    }

    /**
     * 登录
     * @param params 查询参数
     * @return 用户信息
     */
    @Override
    public LoginResponse login(LoginParams params){
        String email = null;
        String phone = null;
        String account = params.getAccount();
        String password = params.getPassword();

        if(account.matches(Validation.PHONE)){
            phone = account;
        }
        if(account.matches(Validation.EMAIL)){
            email = account;
        }
        // 校验用户登录信息
        Users user = usersDatabase.findUserForLogin(email, phone);

        // 登录失败，用户账号不存在
        if(user == null){
            throw new RequestException(ErrorCode.LOGIN_ERROR);
        }

        boolean isLogin = bCryptPasswordEncoder.matches(password, user.getPassword());

        // 登录失败，密码不正确，判断失败次数，超出限制时禁止登录 todo
        if(isLogin){
            throw new RequestException(ErrorCode.LOGIN_ERROR);
        }

        // 登录成功
        String username = user.getUserName();
        String userId = user.getId();

        // 生成token
        UserToken userToken = userTokenService.createUserToken(username, userId, params.getIsRememberMe());
        String token = userToken.getToken();

        // 添加登录记录 todo
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUserId(userId);
        loginRecord.setIp("N/A");
        loginRecord.setEquipment("N/A");
        loginRecord.setPlace("N/A");
        loginRecordDatabase.insertLoginRecord(loginRecord);

        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return response;
    }

    /**
     * 查询用户列表
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户列表
     */
    @Override
    public List<Users> queryUserList(ApiAuth auth, QueryUsersListParams params){
        String teamId = auth.getTeamId();

        Byte isDelete = null;
        String deleteCode = UserDeleteEnum.DELETE.getCode().toString();
        if(StringUtils.isNotBlank(params.getDelete()) && deleteCode.equals(params.getDelete())){
            isDelete = UserDeleteEnum.DELETE.getCode();
        }

        List<String> statusList = new ArrayList<String>();
        if(StringUtils.isNotBlank(params.getStatus())){
            statusList.add(params.getStatus());
        }

        FindUsersParams findUsersParams = new FindUsersParams();
        findUsersParams.setUserId(params.getUserId());
        findUsersParams.setPhone(params.getPhone());
        findUsersParams.setEmail(params.getEmail());
        findUsersParams.setStatusList(statusList);
        findUsersParams.setDelete(isDelete);
        findUsersParams.setTeamId(teamId);
        return usersDatabase.findUsers(findUsersParams);
    }

    /**
     * 通过ID查询指定用户
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户信息
     */
    @Override
    public Users queryUserById(ApiAuth auth, QueryUserByIdParams params){
        String teamId = auth.getTeamId();

        FindUsersParams findUsersParams = new FindUsersParams();
        findUsersParams.setUserId(params.getUserId());
        findUsersParams.setTeamId(teamId);
        List<Users> usersList = usersDatabase.findUsers(findUsersParams);

        Users response = null;
        if(CollectionUtils.isNotEmpty(usersList)){
            response = usersList.get(0);
        }
        return response;
    }
}
