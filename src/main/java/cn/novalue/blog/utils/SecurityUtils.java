package cn.novalue.blog.utils;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.security.entity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 安全工具类，提供用户、权限、安全等工具
 *
 * @author Wu Yangjie
 * @date 2020-03-08
 */
public class SecurityUtils {

    public static User getUser() {
        return ((MyUserDetails)getAuthentication().getPrincipal()).getUser();
    }
    public static Authentication getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new BadCredentialsException("认证信息错误：未认证"));
    }
    /*
     * Spring Security对于未登录用户会生成一个默认的AnonymousAuthenticationToken，
     * 所以这里判断是否登录还要加上这一层判断
     */
    public static boolean isAuthenticated() {
        Authentication authentication =  getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return false;
        return authentication.isAuthenticated();

    }
}
