package cn.novalue.blog;

import cn.novalue.blog.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("张三");
            user.setPassword("123456" + i);
            list.add(user);
        }

        redisTemplate.opsForValue().set("test", list);
        List<User> s = (List<User>)redisTemplate.opsForValue().get("test");
        System.out.println(s);
    }

}
