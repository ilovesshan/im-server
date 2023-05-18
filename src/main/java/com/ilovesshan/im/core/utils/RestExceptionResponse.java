package com.ilovesshan.im.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/4/23
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestExceptionResponse {
    private int code;
    private String message;
    private String requestId;
    private String requestPath;
    private String requestTime;
}