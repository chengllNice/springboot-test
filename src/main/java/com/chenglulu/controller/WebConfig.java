package com.chenglulu.controller;

import com.chenglulu.Interceptor.AuthInterceptor;
import com.chenglulu.Interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration auth = registry.addInterceptor(authInterceptor);
        InterceptorRegistration base = registry.addInterceptor(baseInterceptor);

        auth.addPathPatterns("/**");
        base.addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
