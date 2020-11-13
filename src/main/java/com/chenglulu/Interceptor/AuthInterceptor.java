package com.chenglulu.Interceptor;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.users.domain.QueryUserByIdParams;
import com.chenglulu.exception.AuthorizedException;
import com.chenglulu.exception.ForbiddenException;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.service.UsersService;
import com.chenglulu.service.database.UsersDatabase;
import com.chenglulu.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if(!this.hasPermission(request, handler)){
            throw new ForbiddenException(ErrorCode.AUTHORIZATION_INVALID);
        }
        return true;
    }

    private boolean hasPermission(HttpServletRequest request, Object handler){
        final String authorization = request.getHeader(Constants.Authorization);

        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);

            // 如果方法上没有注解则获取类上的注解
            if(requiredPermission == null){
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }

            // 如果注解存在，验证auth权限并解析
            if(requiredPermission != null){
                // authorization不存在或者不是以Bearer开头
                if(StringUtils.isBlank(authorization) || !authorization.startsWith(Constants.AuthorizationPrefix)){
                    throw new AuthorizedException(ErrorCode.USER_NOT_LOGIN);
                }

                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                String token = request.getHeader(Constants.Authorization);
                token = token.replace(Constants.TOKEN_PREFIX, "");

                String userId = JwtTokenUtils.getUserId(token);

                QueryUserByIdParams queryUserByIdParams = new QueryUserByIdParams();
                queryUserByIdParams.setUserId(userId);
                Users userInfo = usersService.queryUserById(queryUserByIdParams);

                if(userInfo == null){
                    return false;
                }

                request.setAttribute(Constants.REQUEST_USER_TOKEN, token);
                request.setAttribute(Constants.REQUEST_USER_INFO, userInfo);
            }
        }
        return true;
    }
}
