package cn.novalue.blog;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MailService mailService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    public void testEvent() {
        publisher.publishEvent(new MyEvent(1));
    }

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

    @Test
    public void testMail() {
        Map<String, Object> content = new HashMap<>();
        content.put("email", "email");
        content.put("code", "123");
        System.out.println("start");
        mailService.sendTemplateMail("1326670425@qq.com", "test", content, "mail.ftl", null);
        System.out.println("end");
    }
}
