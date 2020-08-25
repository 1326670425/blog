package cn.novalue.blog;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@SpringBootTest
public class Test {
    @Autowired
    private UserService userService;

    @org.junit.jupiter.api.Test
    public void test() {
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUsername("zhangsan");
        user.setPassword(encoder.encode("zhangsan"));
        userService.save(user);
    }

    public static void main(String[] args) {
        Byte b = 1;
        Long l = (long)b;
    }
}
