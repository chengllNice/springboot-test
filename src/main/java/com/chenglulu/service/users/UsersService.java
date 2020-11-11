package com.chenglulu.service.users;

import com.chenglulu.constant.ErrorCode;
import com.chenglulu.constant.Validation;
import com.chenglulu.controller.users.UsersController;
import com.chenglulu.controller.users.domain.*;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.dao.UsersMapper;
import com.chenglulu.mybatis.entity.LoginRecord;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.service.users.database.DatabaseLoginRecord;
import com.chenglulu.service.users.database.DatabaseUsers;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.JwtTokenUtils;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Service
@Slf4j
public class UsersService {

    @Autowired(required=false)
    private UsersMapper usersMapper;

    @Autowired
    private DatabaseUsers databaseUsers;

    @Autowired
    private DatabaseLoginRecord databaseLoginRecord;

    @Autowired
    private UsersConfig usersConfig;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    /**
     * 注册
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    public Users registerUser(ApiAuth auth, RegisterUsersParams params){
        return databaseUsers.insertUser(params);
    }

    /**
     * 登录
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户信息
     */
    public LoginResponse login(ApiAuth auth, LoginParams params){
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
        Users user = databaseUsers.findUserForLogin(email, phone, password);

        if(user == null){
            throw new RequestException(ErrorCode.LOGIN_ERROR);
        }
        String userId = user.getId();

        // 生成token
        String token = JwtTokenUtils.createToken(user.getUserName(), userId, params.getIsRememberMe());

        List<LoginRecord> loginRecords = databaseLoginRecord.findLoginRecordByUserId(user.getId());;

        String userLoginMaxLimit = usersConfig.getUserLoginMaxLimit();

        if(loginRecords.size() >= Integer.parseInt(userLoginMaxLimit)){
            throw new RequestException(ErrorCode.LOGIN_Limit_ERROR);
        }

        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUserId(userId);
        loginRecord.setIp("N/A");
        loginRecord.setEquipment("N/A");
        loginRecord.setPlace("N/A");
        loginRecord.setToken(token);
        databaseLoginRecord.insertLoginRecord(loginRecord);

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
    public List<Users> queryUserList(ApiAuth auth, QueryUsersListParams params){
        FindUsersParams findUsersParams = new FindUsersParams();
        findUsersParams.setId(params.getId());
        findUsersParams.setPhone(params.getPhone());
        findUsersParams.setEmail(params.getEmail());
        return databaseUsers.findUsers(findUsersParams);
    }

    /**
     * 通过ID查询指定用户
     * @param auth ApiAuth
     * @param params 查询参数
     * @return 用户信息
     */
    public Users queryUserById(ApiAuth auth, QueryUserByIdParams params){
        FindUsersParams findUsersParams = new FindUsersParams();
        findUsersParams.setId(params.getId());
        List<Users> usersList = databaseUsers.findUsers(findUsersParams);

        Users response = null;
        if(CollectionUtils.isNotEmpty(usersList)){
            response = usersList.get(0);
        }
        return response;
    }
}
