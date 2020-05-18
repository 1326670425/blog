package cn.novalue.blog.service;

import cn.novalue.blog.model.vo.MessageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.Message;

import java.io.Serializable;

/**
 * 短消息表(Message)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
public interface MessageService extends IService<Message> {
    IPage<MessageVO> getMessageByPage(Page<?> page, Long userId);
    boolean removeMessage(Long id);
}