package cn.novalue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.UserDao;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}