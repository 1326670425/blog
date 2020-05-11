package cn.novalue.blog.security.handler;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.utils.JSONUtils;
import cn.novalue.blog.utils.MyBeanUtils;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 *
 * @author Wu Yangjie
 * @date 2020-03-03
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 设置返回结果的格式
        // 弃用的原因是官方认为主流浏览器默认UTF-8了，这里为了兼容中文，还是声明一下
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 这里后续考虑是否需要将用户角色返回前台做处理
        // ((UserDetails)authentication.getPrincipal()).getAuthorities().stream().map(GrantedAuthority::getAuthority).forEach(System.out::println);
        User user = SecurityUtils.getUser();
        UserVO userVO = new UserVO();
        MyBeanUtils.copy(user, userVO);
        httpServletResponse.getWriter().write(JSONUtils.objectToJSON(Response.success("登录成功", userVO)));
    }
}
