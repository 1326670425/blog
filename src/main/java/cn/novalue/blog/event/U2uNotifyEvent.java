package cn.novalue.blog.event;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * 用户对用户通知事件
 *
 * @author Wu Yangjie
 * @date 2020-07-12
 */
public class U2uNotifyEvent extends ApplicationEvent {
    private final U2uNotify u2uNotify;
    private final User sender;
    public U2uNotifyEvent(U2uNotify notify, User sender) {
        super(notify);
        this.u2uNotify = notify;
        this.sender = sender;
    }
    public U2uNotify getU2uNotify() {
        return this.u2uNotify;
    }
    public User getSender() {
        return this.sender;
    }
}
