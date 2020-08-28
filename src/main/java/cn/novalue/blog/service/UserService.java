package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.Role;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.User;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
public interface UserService extends IService<User> {
    User selectOne(String s);

    UserVO firstLogin(User user);

    List<Role> selectUserRoles(Long userId);

    boolean register(String username, String password);

    User findByUsername(String username);
}