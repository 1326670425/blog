package cn.novalue.blog.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Spring Security默认使用这种加密方式来对密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 创建基于内存的用户认证控制
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("root")
                .password(encoder.encode("root"))
                .roles("ADMIN")
                .and()
                .withUser("normal")
                .password(encoder.encode("normal"))
                .roles("NORMAL");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }
}
