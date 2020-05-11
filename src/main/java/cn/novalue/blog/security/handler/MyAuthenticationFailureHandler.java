package cn.novalue.blog.security.handler;

import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证失败处理器
 *
 * @author Wu Yangjie
 * @date 2020-03-03
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // String msg = e.getMessage();
        String msg = "用户名或密码错误";
        log.warn("authenticate failure : {}", msg);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(JSONUtils.objectToJSON(Response.failure(401, msg)));
    }
}
