package cn.novalue.blog;

import cn.novalue.blog.model.entity.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@SpringBootTest
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        User user = new User();
        user.setUsername("张三");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("zhangsan"));
        System.out.println(user);
    }
}
