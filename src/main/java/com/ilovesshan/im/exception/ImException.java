package com.ilovesshan.im.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/4/23
 * @description:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ImException extends RuntimeException {
    private String errMessage;

    public ImException(String message) {
        super(message);
        this.errMessage = message;
    }
}