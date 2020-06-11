package cn.novalue.blog.security.service;

import cn.novalue.blog.model.entity.Role;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.security.entity.MyUserDetails;
import cn.novalue.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义用户登录认证
 *
 * @author Wu Yangjie
 * @date 2020-03-03
 */
@Slf4j
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {

        log.info("start loading user from database");
        User user = userService.selectOne(s);
        if (user == null) {
            log.warn("user {} not found", s);
            throw new UsernameNotFoundException("找不到该用户");
        }
        // 加载用户角色信息
        List<Role> roles = userService.selectUserRoles(user.getId());
        List<? extends GrantedAuthority> authorities = roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        MyUserDetails userDetails = new MyUserDetails(user);
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
