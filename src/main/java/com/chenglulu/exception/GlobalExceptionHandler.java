package com.chenglulu.exception;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.controller.BaseResponse;
import com.chenglulu.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 服务内部异常处理
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerServiceException(HttpServletRequest request, ServiceException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler ServiceException. requestId = {}", requestId, ex);

        String code = ex.getCode();
        Object[] msgArg = ex.getMsgArgs();

        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 无权限访问
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {ForbiddenException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerForbiddenException(HttpServletRequest request, ForbiddenException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler ForbiddenException. requestId = {}", requestId, ex);

        String code = ex.getCode();
        Object[] msgArg = ex.getMsgArgs();

        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 权限校验异常
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {AuthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerAuthorizedException(HttpServletRequest request, AuthorizedException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler AuthorizedException. requestId = {}", requestId, ex);

        String code = ex.getCode();
        Object[] msgArg = ex.getMsgArgs();

        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 数据绑定异常处理
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {RequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerRequestException(HttpServletRequest request, RequestException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler RequestException. requestId = {}", requestId, ex);

        String code = ex.getCode();
        Object[] msgArg = ex.getMsgArgs();

        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 数据绑定异常处理
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerBindException(HttpServletRequest request, BindException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler BindException. requestId = {}", requestId, ex);
        String code = ErrorCode.PARAMETER_ERROR;

        BindingResult br = ex.getBindingResult();
        StringBuilder msgStr = new StringBuilder();
        List<FieldError> feList = br.getFieldErrors();
        for (FieldError fe : feList) {
            String defaultMessage = fe.getDefaultMessage();
            String field = fe.getField();

            if(defaultMessage != null && defaultMessage.contains("validtor")){
                Object[] msgArg = fe.getArguments();
                String message = getLocaleMessage(defaultMessage, msgArg);
                msgStr.append(message).append("; ");
            }else {
                msgStr.append(field).append(" ").append(defaultMessage).append("; ");
            }
        }
        Object[] msgArg = new Object[]{msgStr};
        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 参数验证异常处理
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler MethodArgumentNotValidException. requestId = {}", requestId, ex);
        String code = ErrorCode.PARAMETER_ERROR;

        BindingResult br = ex.getBindingResult();
        StringBuilder msgStr = new StringBuilder();

        if(br.hasErrors()){
            List<FieldError> feList = br.getFieldErrors();
            for (FieldError fe : feList) {
                String defaultMessage = fe.getDefaultMessage();
                String field = fe.getField();

                if(defaultMessage != null && defaultMessage.contains("validtor")){
                    Object[] msgArg = fe.getArguments();
                    String message = getLocaleMessage(defaultMessage, msgArg);
                    msgStr.append(message).append("; ");
                }else {
                    msgStr.append(field).append(" ").append(defaultMessage).append("; ");
                }
            }
        }

        Object[] msgArg = new Object[]{msgStr};
        String message = getLocaleMessage(code, msgArg);

        BaseResponse res = ResponseUtil.error(requestId, code, message);
        return new HttpEntity<BaseResponse>(res);
    }


    /**
     * 未知异常处理
     * @param request 请求
     * @param ex 异常对象
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public HttpEntity<BaseResponse> handlerException(HttpServletRequest request, Exception ex){
        String requestId = getRequestIdHeader(request);
        logger.error("GlobalExceptionHandler Exception. requestId = {}", requestId, ex);

        String code = ErrorCode.UNKNOWN_ERROR;
        String message = getLocaleMessage(code, null);
        BaseResponse res = ResponseUtil.error(requestId, code, message);

        return new HttpEntity<BaseResponse>(res);
    }

    /**
     * 获取reqID
     * @param request 请求
     * @return String
     */
    private String getRequestIdHeader(HttpServletRequest request) {
        return request.getHeader(Constants.HEADER_X_REQUEST_ID);
    }

    /**
     * 获取本地配置的语言文件
     * @param code 键
     * @param msgArg Object[]
     * @return String
     */
    private String getLocaleMessage(String code, Object[] msgArg) {
        Locale locale = LocaleContextHolder.getLocale();
        if(locale == null){
            locale = Locale.US;
        }
        return messageSource.getMessage(code, msgArg, locale);
    }
}
