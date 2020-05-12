package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.vo.MessageVO;
import cn.novalue.blog.service.ChildCommentService;
import cn.novalue.blog.service.RootCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.MessageDao;
import cn.novalue.blog.model.entity.Message;
import cn.novalue.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短消息表(Message)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private ChildCommentService childCommentService;

    @Override
    public IPage<MessageVO> getMessageByPage(Page<?> page) {
        IPage<MessageVO> pageMessage = messageDao.getMessageByPage(page);
        List<MessageVO> messages = pageMessage.getRecords();
        for (MessageVO messageVO : messages) {
            messageVO.setCommentNum(rootCommentService.getCount(messageVO.getId(), CommentType.MESSAGE));
        }
        pageMessage.setRecords(messages);
        return pageMessage;
    }

    @Override
    public IPage<MessageVO> getUserMessageByPage(Page<?> page, Long userId) {
        IPage<MessageVO> pageMessage = messageDao.getUserMessageByPage(page, userId);
        List<MessageVO> messages = pageMessage.getRecords();
        for (MessageVO messageVO : messages) {
            messageVO.setCommentNum(rootCommentService.getCount(messageVO.getId(), CommentType.MESSAGE));
        }
        pageMessage.setRecords(messages);
        return pageMessage;
    }

    @Override
    public boolean removeMessage(Long id) {
        boolean flag = removeById(id);
        List<Long> rootCommentIds = rootCommentService.getIdLists(id, CommentType.MESSAGE);
        flag &= rootCommentService.removeByIds(rootCommentIds);
        flag &= childCommentService.removeByParents(rootCommentIds);
        return flag;
    }
}