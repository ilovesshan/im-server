package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.mapper.UserMapper;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.model.vo.UserVo;
import com.ilovesshan.im.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private  UserMapper userMapper;

    @Override
    public List<FriendVo> queryUser(String kw) {
        return userMapper.selectByIdOrUsername(kw);
    }

    @Override
    public UserVo queryUserById(long id) {
        return userMapper.selectByIds(id);
    }
}
