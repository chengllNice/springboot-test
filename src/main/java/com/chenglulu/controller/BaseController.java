package com.chenglulu.controller;

import com.chenglulu.constant.Constants;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.CommonUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected ApiAuth generateAuth(HttpServletRequest request){
        ApiAuth auth = new ApiAuth();

        String requestId = request.getHeader(Constants.HEADER_X_REQUEST_ID);
        String requestTime = request.getHeader(Constants.REQUEST_START_TIME);
        String token = request.getHeader(Constants.REQUEST_USER_TOKEN);
        Users userInfo = (Users) request.getAttribute(Constants.REQUEST_USER_INFO);

        String userId = null;
        if(userInfo != null){
            userId = userInfo.getId();
        }

        if(StringUtils.isEmpty(requestId)){
            requestId = CommonUtils.getUuid();
        }

        auth.setRequestId(requestId);
        auth.setUserId(userId);
        auth.setRequestTime(requestTime);
        auth.setToken(token);
//        auth.setRealIp(requestTime);
        return auth;
    }
}
