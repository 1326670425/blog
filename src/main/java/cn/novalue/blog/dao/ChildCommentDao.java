package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.model.vo.MessageVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.ChildComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 二级评论表(ChildComment)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Repository
public interface ChildCommentDao extends BaseMapper<ChildComment> {
    IPage<CommentVO> getChildCommentByPage(Page<?> page, @Param("parent_id") Long parentId);
    boolean removeByParents(List<Long> parentIds);
}