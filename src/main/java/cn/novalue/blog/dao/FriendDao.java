package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.Friend;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 好友表(Friend)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
@Repository
public interface FriendDao extends BaseMapper<Friend> {

    List<UserVO> getFriends(@Param("userId") Long userId, @Param("group") String group);
}