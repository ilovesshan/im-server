package com.ilovesshan.im.core.handler;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.core.utils.RestExceptionResponse;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/4/23
 * @description:
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理自定义异常
    @ExceptionHandler(ImException.class)
    @ResponseStatus(HttpStatus.OK)
    public R handleImException(ImException e) {
        log.error("ImException 自定义异常：", e);
        return R.fail(e.getMessage());
    }

    // 处理JSR303异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        log.error("MethodArgumentNotValidException JSR303异常：", e);
        return R.fail(error.getDefaultMessage());
    }

    // 处理容错异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionResponse handleException(Exception e) {
        if (e instanceof JwtException) {
            log.error("JwtException token解析异常：", e);
            return new RestExceptionResponse(R.ERROR_CODE_AUTHORIZATION, R.ERROR_INSUFFICIENT_AUTHENTICATION, MDC.get("requestId"), MDC.get("requestPath"), MDC.get("requestTime"));
        } else {
            log.error("Exception 容错异常：", e);
            return new RestExceptionResponse(R.ERROR_CODE_INTERNAL_SERVER, R.ERROR_MESSAGE_INTERVAL, MDC.get("requestId"), MDC.get("requestPath"), MDC.get("requestTime"));
        }
    }
}