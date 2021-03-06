package cn.novalue.blog.service;

import cn.novalue.blog.handler.U2uNotifyHandler;
import cn.novalue.blog.model.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.Friend;

import java.util.List;

/**
 * 好友表(Friend)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
public interface FriendService extends IService<Friend>, U2uNotifyHandler {
    boolean addFriend(Long userId1, Long userId2);
    List<UserVO> getFriends(Long currentUserId, String group);
}