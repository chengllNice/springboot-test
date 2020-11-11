package com.chenglulu.Interceptor;


import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.exception.RequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 设置请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute(Constants.REQUEST_START_TIME, startTime);

//        return super.preHandle(request, response, handler);
        return true;
    }
}
