package com.ilovesshan.im.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Order(-1)
@WebFilter("/*")
public class TraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 生成日志ID和请求时间 并且将Trace信息存储到Slf4j中，配合日志文件模板打印
        String requestId = UUID.randomUUID().toString().replaceAll("-", "");
        String requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String requestPath = ((HttpServletRequest) servletRequest).getServletPath();
        MDC.put("requestId", requestId);
        MDC.put("requestPath", requestPath);
        MDC.put("requestTime", requestTime);
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }
}
