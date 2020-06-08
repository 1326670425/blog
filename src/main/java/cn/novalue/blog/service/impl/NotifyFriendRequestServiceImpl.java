package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.Friend;
import cn.novalue.blog.service.FriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.NotifyFriendRequestDao;
import cn.novalue.blog.model.entity.NotifyFriendRequest;
import cn.novalue.blog.service.NotifyFriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            long userId1 = friendRequest.getSender();
            long userId2 = friendRequest.getReceiver();
            // 保证userId1小于userId2，防止出现重复
            // 不过暂时还没有去重检查
            if (userId1 > userId2) {
                long i = userId1;
                userId1 = userId2;
                userId2 = i;
            }
            Friend friend = new Friend();
            friend.setUserId1(userId1);
            friend.setUserId2(userId2);
            result = friendService.save(friend);
        }
        return result;
    }
}