package com.chenglulu.controller.roles;

import com.chenglulu.Interceptor.RequiredPermission;
import com.chenglulu.controller.BaseController;
import com.chenglulu.controller.BaseResponse;
import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.roles.domain.FindRolesParams;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.service.RolesService;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredPermission
@RequestMapping( value = "/roles", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RolesController extends BaseController {

    @Autowired
    private RolesService rolesService;

    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResponse createRoles(HttpServletRequest request, @Validated @RequestBody CreateRolesParams params){
        ApiAuth auth = generateAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, createRoles start auth = {} params = {}", requestId, auth, params);
        Roles result = rolesService.createRoles(auth, params);
        logger.info("requestId = {}, createRoles end result = {}", requestId, result);
        return ResponseUtil.success(request, requestId, result);
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResponse queryRolesList(HttpServletRequest request, @Validated @RequestBody FindRolesParams params){
        ApiAuth auth = generateAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, queryRolesList start auth = {} params = {}", requestId, auth, params);
        List<Roles> result = rolesService.queryRolesList(auth, params);
        logger.info("requestId = {}, queryRolesList end result = {}", requestId, result);
        return ResponseUtil.success(request, requestId, result);
    }
}
