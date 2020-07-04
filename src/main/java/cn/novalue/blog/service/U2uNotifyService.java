package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.enums.U2uNotifyType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 用户对用户通知表(U2uNotify)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-07-04
 */
public interface U2uNotifyService extends IService<U2uNotify> {
    /**
     * 获取未读通知消息总数
     *
     * @return 未读通知总数
     */
    Integer getCount();

    /**
     * 按类型获取未读通知消息总数
     * @return 按类型获取未读通知消息总数，key为类型名，value为该类型未读消息个数
     */
    Map<String, Integer> getTypeCount();

    List<U2uNotify> getNotifyByType(U2uNotifyType notifyType);

    Boolean handleU2uNotify(U2uNotify u2uNotify);
}