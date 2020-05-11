package cn.novalue.blog.security.handler;

import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限拒绝处理
 * @author Wu Yangjie
 * @date 2020-05-06
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String msg = "无权访问";
        log.warn("authenticate failure : {}", msg);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(JSONUtils.objectToJSON(Response.failure(401, msg)));
    }
}
