package cn.novalue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.NotifyFriendRequest;

/**
 * 好友申请通知表(NotifyFriendRequest)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
public interface NotifyFriendRequestService extends IService<NotifyFriendRequest> {

    Boolean handleRequest(NotifyFriendRequest friendRequest);
}