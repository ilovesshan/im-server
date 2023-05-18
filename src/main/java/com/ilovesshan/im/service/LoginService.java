package com.ilovesshan.im.service;

import com.ilovesshan.im.model.dto.UserDto;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface LoginService {

    Map<String,Object> login(UserDto loginParams);
}
