package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.core.utils.JwtUtil;
import com.ilovesshan.im.mapper.UserMapper;
import com.ilovesshan.im.model.dto.UserDto;
import com.ilovesshan.im.model.po.User;
import com.ilovesshan.im.model.vo.UserVo;
import com.ilovesshan.im.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(UserDto loginParams) {
        User user = userMapper.selectUserByNameAndPwd(loginParams.getUsername(), loginParams.getPassword());
        if (Objects.isNull(user)) {
            throw new ImException("用户名或者密码错误");
        }
        // 生成token
        String token = JwtUtil.generatorToken(user);

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", userVo);
        hashMap.put("token", token);
        return hashMap;
    }
}
