package com.ilovesshan.im.handler;

import com.ilovesshan.im.exception.ImException;
import com.ilovesshan.im.utils.R;
import com.ilovesshan.im.utils.RestExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
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
    
    @ExceptionHandler(ImException.class)
    @ResponseStatus(HttpStatus.OK)
    public R handleImException(ImException e) {
        log.error(e.getMessage(), e);
        return R.fail(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new RestExceptionResponse(R.ERROR_CODE_INTERNAL_SERVER, e.getMessage(), MDC.get("requestId"), MDC.get("requestPath"), MDC.get("requestTime"));
    }
}