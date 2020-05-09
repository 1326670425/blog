package cn.novalue.blog;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        user.setUsername("zhangsan");
        userService.save(user);
    }
}
