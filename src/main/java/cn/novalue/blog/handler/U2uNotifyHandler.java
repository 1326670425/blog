package cn.novalue.blog.handler;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.support.Response;

/**
 * 用户对用户通知处理接口
 * @author Wu yangjie
 * @date 2020-07-04
 */
public interface U2uNotifyHandler {
    /**
     * 处理通知消息
     * @param u2uNotify 通知实体
     * @return 处理结果
     */
    Response handle(U2uNotify u2uNotify);

    /**
     * 获取具体通知类型
     * @return 通知类型
     */
    U2uNotifyType getHandlerType();
}
