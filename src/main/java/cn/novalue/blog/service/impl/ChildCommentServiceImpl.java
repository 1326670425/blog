package cn.novalue.blog.service.impl;

import cn.novalue.blog.event.U2uNotifyEvent;
import cn.novalue.blog.model.entity.*;
import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.service.LikeService;
import cn.novalue.blog.service.RootCommentService;
import cn.novalue.blog.service.U2uNotifyService;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ChildCommentDao;
import cn.novalue.blog.service.ChildCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 二级评论表(ChildComment)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Slf4j
@Service("childCommentService")
public class ChildCommentServiceImpl extends ServiceImpl<ChildCommentDao, ChildComment> implements ChildCommentService {

    @Autowired
    private ChildCommentDao childCommentDao;
    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public IPage<CommentVO> getCommentPage(Page page, String orderItem, Long parentId) {
        if (!StringUtils.isEmpty(orderItem) && orderItem.equalsIgnoreCase("like"))
            page.addOrder(OrderItem.desc("like_num"));
        page.addOrder(OrderItem.desc("create_time"));
        IPage<CommentVO> commentPage = childCommentDao.getChildCommentByPage(page, parentId);
        List<CommentVO> commentVOS = commentPage.getRecords();
        for (CommentVO commentVO : commentVOS) {
            likeService.handleVO(commentVO, 3);
        }
        commentPage.setRecords(commentVOS);
        return commentPage;
    }

    @Override
    public boolean removeByParents(List<Long> parentIds) {
        boolean isSuccess = childCommentDao.removeByParents(parentIds);
        if (!isSuccess) {
            log.warn("删除二级评论失败，对应的一级评论id：{}", parentIds.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean add(ChildComment comment) {
        RootComment rootComment = rootCommentService.getById(comment.getParentId());
        if (rootComment == null) {
            log.warn("对应的一级评论不存在");
            return false;
        }
        U2uNotify notify = new U2uNotify();
        notify.setReceiver(rootComment.getUserId());
        notify.setTargetId(rootComment.getId());
        notify.setTargetType("root_comment");
        notify.setTargetDesc(rootComment.getContent());
        notify.setType(U2uNotifyType.REPLY.name().toLowerCase());
        boolean result = save(comment);
        eventPublisher.publishEvent(new U2uNotifyEvent(notify, SecurityUtils.getUser()));
        return result;
    }

    /* 处理回复通知 */
    @Override
    public Response handle(U2uNotify u2uNotify) {
        Long rootCommentId = u2uNotify.getTargetId();
        return Response.success();
    }

    @Override
    public U2uNotifyType getHandlerType() {
        return U2uNotifyType.REPLY;
    }
}