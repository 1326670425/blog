package cn.novalue.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.NotifyFriendRequest;
import org.springframework.stereotype.Repository;

/**
 * 好友申请通知表(NotifyFriendRequest)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
@Repository
public interface NotifyFriendRequestDao extends BaseMapper<NotifyFriendRequest> {

}