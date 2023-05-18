package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.mapper.UserMapper;
import com.ilovesshan.im.model.po.User;
import com.ilovesshan.im.service.LoginService;
import com.ilovesshan.im.core.utils.JwtUtil;
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

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Map<String, Object> login(Map<String, String> loginParams) {
        String username = loginParams.get("username");
        String password = loginParams.get("password");
        User user = userMapper.selectUserByNameAndPwd(username, password);
        if (Objects.isNull(user)) {
            throw new ImException("用户名或者密码错误");
        }

        // 删除用户密码并生成token
        user.setPassword("");
        String token = jwtUtil.generatorToken(user);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", user);
        hashMap.put("token", token);
        return hashMap;
    }
}
