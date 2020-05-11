package cn.novalue.blog.security.handler;

import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.utils.JSONUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理器
 *
 * @author Wu Yangjie
 * @date 2020-04-19
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(JSONUtils.objectToJSON(Response.success("成功注销")));
    }
}
