package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.Role;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.utils.MyBeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.UserDao;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> selectUserRoles(Long userId) {
        return userDao.selectUserRoles(userId);
    }

    @Override
    public boolean register(String username, String password) {
        User user = selectOne(username);
        if (null != user) {
            return false;
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return save(user);
    }

    @Override
    public User selectOne(String s) {
        return userDao.selectOne(s);
    }

    @Override
    public UserVO firstLogin(User user) {
        LocalDate lastLogin = user.getLastLogin().toLocalDate();
        UserVO userVO = new UserVO();
        MyBeanUtils.copy(user, userVO);
        LocalDate now = LocalDate.now();
        // 不是今日首次登录
        if (now.compareTo(lastLogin) <= 0)
            return userVO;
        // 今日首次登录，这里保留做首次登录逻辑，暂时只是更新了上次登录时间
        // 更新上次登录时间
        user.setLastLogin(LocalDateTime.now());
        return userVO;
    }
}