package com.ilovesshan.im.core.handler;

import com.ilovesshan.im.core.config.CurrentUser;
import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.core.utils.JwtUtil;
import com.ilovesshan.im.model.po.User;
import com.ilovesshan.im.core.utils.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Configuration
public class CurrentUserMethodArgumentResolverHandler implements HandlerMethodArgumentResolver {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(CurrentUser.class) && methodParameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new ImException(R.ERROR_INSUFFICIENT_AUTHENTICATION);
        }
        return jwtUtil.parseToken(token);
    }
}
