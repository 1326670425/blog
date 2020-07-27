package cn.novalue.blog.service;

import cn.novalue.blog.handler.U2uNotifyHandler;
import cn.novalue.blog.model.vo.CommentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.ChildComment;

import java.util.List;

/**
 * 二级评论表(ChildComment)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
public interface ChildCommentService extends IService<ChildComment>, U2uNotifyHandler {
    IPage<CommentVO> getCommentPage (Page page, String orderItem, Long parentId);
    boolean removeByParents(List<Long> parentIds);
    boolean add(ChildComment comment);
}