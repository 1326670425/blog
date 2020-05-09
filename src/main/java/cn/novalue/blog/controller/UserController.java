package cn.novalue.blog.controller;



import cn.novalue.blog.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户表(User)表控制层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

}