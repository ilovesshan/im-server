package com.ilovesshan.im.controller;

import com.ilovesshan.im.service.LoginService;
import com.ilovesshan.im.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Api(tags = "用户模块")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> loginParams) {
        Map<String, Object> loginResult = loginService.login(loginParams);
        return R.success(R.SUCCESS_MESSAGE_LOGIN, loginResult);
    }
}
