package com.ilovesshan.im.core.handler;

import com.ilovesshan.im.core.config.UserCache;
import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.core.utils.JwtUtil;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.model.po.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization) && authorization.contains("Bearer ")) {
            throw new ImException(R.ERROR_NO_AUTHENTICATION);
        }
        // 校验Token有效性
        String token = authorization.replace("Bearer ", "");
        User user = JwtUtil.parseToken(token);

        // 存到ThreadLocal中
        UserCache.set("userId", String.valueOf(user.getId()));
        UserCache.set("token", token);
        return true;
    }
}
