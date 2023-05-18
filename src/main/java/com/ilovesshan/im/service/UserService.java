package com.ilovesshan.im.service;

import com.ilovesshan.im.model.vo.FriendVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface UserService {
    FriendVo queryUser(String kw);
}
