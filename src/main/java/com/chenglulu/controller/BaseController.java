package com.chenglulu.controller;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.exception.RequestException;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.CommonUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected ApiAuth initAuth(HttpServletRequest request){
        ApiAuth auth = new ApiAuth();

        String requestId = request.getHeader(Constants.HEADER_X_REQUEST_ID);
        String requestTime = request.getHeader(Constants.REQUEST_START_TIME);

        if(StringUtils.isEmpty(requestId)){
            requestId = CommonUtils.getUuid();
        }

        auth.setRequestId(requestId);
        auth.setRequestTime(requestTime);
//        auth.setRealIp(requestTime);
        return auth;
    }

    protected ApiAuth generateAuth(HttpServletRequest request){
        ApiAuth auth = initAuth(request);

        String token = request.getHeader(Constants.REQUEST_USER_TOKEN);
        Users userInfo = (Users) request.getAttribute(Constants.REQUEST_USER_INFO);

        if(userInfo == null){
            throw new RequestException(ErrorCode.USER_NOT_EXIST);
        }

        auth.setUserId(userInfo.getId());
        auth.setTeamId(userInfo.getTeamId());
        auth.setToken(token);
//        auth.setRealIp(requestTime);
        return auth;
    }
}
