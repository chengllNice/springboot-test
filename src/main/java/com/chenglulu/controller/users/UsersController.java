package com.chenglulu.controller.users;

import com.chenglulu.Interceptor.RequiredPermission;
import com.chenglulu.controller.BaseController;
import com.chenglulu.controller.BaseResponse;
import com.chenglulu.controller.users.domain.*;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.service.UsersService;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping( value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UsersController extends BaseController {
    @Autowired
    private UsersService usersService;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResponse registerUser(HttpServletRequest request, @Validated @RequestBody RegisterUsersParams params){
        ApiAuth auth = initAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, registerUser start params = {}", requestId, params);
        Users users = usersService.registerUser(auth, params);
        logger.info("requestId = {}, registerUser end users = {}", requestId, users);
        return ResponseUtil.success(request, requestId, users);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequiredPermission
    public BaseResponse createUser(HttpServletRequest request, @Validated @RequestBody CreateUserParams params){
        ApiAuth auth = generateAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, createUser start auth = {} params = {}", requestId, auth, params);
        Users users = usersService.createUser(auth, params);
        logger.info("requestId = {}, createUser end users = {}", requestId, users);
        return ResponseUtil.success(request, requestId, users);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(HttpServletRequest request, @Validated @RequestBody LoginParams params){
        ApiAuth auth = initAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, login start params = {}", requestId, params);
        LoginResponse loginResponse = usersService.login(auth, params);
        logger.info("requestId = {}, login end loginResponse = {}", requestId, loginResponse);
        return ResponseUtil.success(request, requestId, loginResponse);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiredPermission
    public BaseResponse queryUserList(HttpServletRequest request, @Validated QueryUsersListParams params){
        ApiAuth auth = generateAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, queryUserList start auth = {} params = {}", requestId, auth, params);
        List<Users> usersList = usersService.queryUserList(auth, params);
        logger.info("requestId = {}, queryUserList end usersList = {}", requestId, usersList);
        return ResponseUtil.success(request, requestId, usersList);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @RequiredPermission
    public BaseResponse queryUser(HttpServletRequest request, @Validated QueryUserByIdParams params){
        ApiAuth auth = generateAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, queryUser start auth = {} params = {}", requestId, auth, params);
        Users user = usersService.queryUserById(auth, params);
        logger.info("requestId = {}, queryUser end user = {}", requestId, user);
        return ResponseUtil.success(request, requestId, user);
    }
}
