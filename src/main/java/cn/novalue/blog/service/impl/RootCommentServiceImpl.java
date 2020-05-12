package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.ChildComment;
import cn.novalue.blog.model.entity.Message;
import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.service.ChildCommentService;
import cn.novalue.blog.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.RootCommentDao;
import cn.novalue.blog.model.entity.RootComment;
import cn.novalue.blog.service.RootCommentService;
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
        }
        commentPages.setRecords(comments);
        return commentPages;
    }

    @Override
    public boolean add(RootComment comment) {
        Message message = messageService.getById(comment.getHostId());
        if (message == null) {
            log.warn("所属内容不存在");
            return false;
        }
        return save(comment);
    }
}