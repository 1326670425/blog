package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Repository
public interface UserDao extends BaseMapper<User> {
    List<Role> selectUserRoles(@Param("id") Long id);
    User selectOne(@Param("s") String s);
    User findByUsername(String username);
}