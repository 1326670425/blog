package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.*;
import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.service.*;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.RootCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 一级评论表(RootComment)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Service("rootCommentService")
public class RootCommentServiceImpl extends ServiceImpl<RootCommentDao, RootComment> implements RootCommentService {

    @Autowired
    private RootCommentDao rootCommentDao;
    @Autowired
    private ChildCommentService childCommentService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private U2uNotifyService u2uNotifyService;

    @Override
    public Integer getCount(Long hostId, CommentType type) {
        return rootCommentDao.getCount(hostId, type.getValue());
    }

    @Override
    public List<Long> getIdLists(Long hostId, CommentType type) {
        return rootCommentDao.getIdLists(hostId, type.getValue());
    }

    @Override
    public IPage<CommentVO> getCommentPageByType(Page page, String orderItem, Long hostId, CommentType type) {
        if (!StringUtils.isEmpty(orderItem) && orderItem.equalsIgnoreCase("like"))
            page.addOrder(OrderItem.desc("like_num"));
        page.addOrder(OrderItem.desc("create_time"));
        IPage<CommentVO> commentPages = rootCommentDao.getCommentPageByType(page, hostId, type.getValue());
        List<CommentVO> comments = commentPages.getRecords();
        for (CommentVO commentVO : comments) {
            commentVO.setChildrenNum(childCommentService.count(new QueryWrapper<ChildComment>().eq("parent_id", commentVO.getId())));
            likeService.handleVO(commentVO, 2);
        }
        commentPages.setRecords(comments);
        return commentPages;
    }

    @Override
    public boolean add(RootComment comment) {
        String targetDesc = null;
        Long targetId = null;
        Long receiver = null;
        if (comment.getType() == CommentType.MESSAGE.getValue()) {
            Message message = messageService.getById(comment.getHostId());
            if (message == null) {
                log.warn("所属内容不存在");
                return false;
            }
            targetDesc = message.getContent().substring(0, 20);
            targetId = message.getId();
            receiver = message.getUserId();
        }
        if (comment.getType() == CommentType.ARTICLE.getValue()) {
            Article article = articleService.getById(comment.getHostId());
            if (article == null) {
                log.warn("所属内容不存在");
                return false;
            }
            targetDesc = article.getDescription().substring(0, 20);
            targetId = article.getId();
            receiver = article.getUserId();
        }
        boolean result = save(comment);
        U2uNotify notify = new U2uNotify();
        User user = SecurityUtils.getUser();
        notify.setSender(user.getId());
        notify.setSenderName(user.getUsername());
        notify.setReceiver(receiver);
        notify.setTargetId(targetId);
        notify.setTargetType(CommentType.valueOf(comment.getType()).name().toLowerCase());
        notify.setTargetDesc(targetDesc);
        notify.setType(U2uNotifyType.COMMENT.name().toLowerCase());
        result &= u2uNotifyService.save(notify);
        return result;
    }
}