package cn.novalue.blog.handler;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.enums.U2uNotifyType;

/**
 * 用户对用户通知处理接口
 * @author Wu yangjie
 * @date 2020-07-04
 */
public interface U2uNotifyHandler {
    Boolean handle(U2uNotify u2uNotify);
    U2uNotifyType getHandlerType();
}
