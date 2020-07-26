package cn.novalue.blog.listener;

import cn.novalue.blog.event.U2uNotifyEvent;
import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.service.U2uNotifyService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Wu Yangjie
 * @date 2020-07-12
 */
@Component
public class NotifyListener {
    @Autowired
    private U2uNotifyService u2uNotifyService;

    @EventListener
    @Async
    public void U2uNotifyListener(U2uNotifyEvent u2uNotifyEvent) {
        U2uNotify notify = u2uNotifyEvent.getU2uNotify();
        // 在此处不能通过SecurityContext获取当前用户
        // 因为Spring Security是与线程绑定的，而@Async是另起一个线程
        User currentUser = u2uNotifyEvent.getSender();
        notify.setSender(currentUser.getId());
        notify.setSenderName(currentUser.getUsername());
        u2uNotifyService.save(notify);
    }
}
