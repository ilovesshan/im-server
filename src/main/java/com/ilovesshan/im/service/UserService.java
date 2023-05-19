package com.ilovesshan.im.service;

import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.model.vo.UserVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface UserService {
    List<FriendVo> queryUser(String kw);

    UserVo queryUserById(long id);
}
