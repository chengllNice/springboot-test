package com.chenglulu.utils;

import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.BaseResponse;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


public class ResponseUtil {


    /**
     * 成功且带数据
     * @param requestId 请求ID
     * @param object 响应数据
     * @return BaseResponse
     */
    public static BaseResponse success(HttpServletRequest request, String requestId, Object object){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("/i18n/messages");

        Locale locale = RequestContextUtils.getLocale(request);
        String code = ErrorCode.SUCCESS;
        String message = messageSource.getMessage(code, null, locale);

        BaseResponse res = new BaseResponse();
        res.setRequestId(requestId);
        res.setCode(code);
        res.setMessage(message);
        res.setData(object);
        return res;
    }

    /**
     * 成功但不带数据
     * @param requestId 请求ID
     * @return BaseResponse
     */
    public static BaseResponse success(String requestId){
        return success(null);
    }

    /**
     * 失败
     * @param requestId 请求ID
     * @param code 响应code码
     * @param message 响应message信息
     * @return BaseResponse
     */
    public static BaseResponse error(String requestId, String code, String message){
        BaseResponse res = new BaseResponse();
        res.setRequestId(requestId);
        res.setCode(code);
        res.setMessage(message);
        return res;
    }
}
