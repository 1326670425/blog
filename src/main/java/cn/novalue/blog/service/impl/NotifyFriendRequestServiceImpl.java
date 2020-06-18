package cn.novalue.blog.service.impl;

import cn.novalue.blog.service.FriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.NotifyFriendRequestDao;
import cn.novalue.blog.model.entity.NotifyFriendRequest;
import cn.novalue.blog.service.NotifyFriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 好友申请通知表(NotifyFriendRequest)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
@Service("notifyFriendRequestService")
public class NotifyFriendRequestServiceImpl extends ServiceImpl<NotifyFriendRequestDao, NotifyFriendRequest> implements NotifyFriendRequestService {

    @Autowired
    private FriendService friendService;

    @Override
    public Boolean handleRequest(NotifyFriendRequest friendRequest) {
        int status = friendRequest.getStatus();
        boolean result = false;
        // 同意好友申请
        if (status == 1) {
            Long userId1 = friendRequest.getSender();
            Long userId2 = friendRequest.getReceiver();

            result = friendService.addFriend(userId1, userId2);
        }
        return result;
    }
}