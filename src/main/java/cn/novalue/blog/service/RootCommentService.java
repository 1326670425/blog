package cn.novalue.blog.service;

import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.vo.CommentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.RootComment;

import java.util.List;

/**
 * 一级评论表(RootComment)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
public interface RootCommentService extends IService<RootComment> {
    Integer getCount(Long hostId, CommentType type);
    List<Long> getIdLists(Long hostId, CommentType type);
    IPage<CommentVO> getCommentPageByType(Page page, String orderItem, Long hostId, CommentType type);

    boolean add(RootComment comment);
}