package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.Message;
import cn.novalue.blog.model.entity.RootComment;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.service.LikeService;
import cn.novalue.blog.service.RootCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ChildCommentDao;
import cn.novalue.blog.model.entity.ChildComment;
import cn.novalue.blog.service.ChildCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return save(comment);
    }
}