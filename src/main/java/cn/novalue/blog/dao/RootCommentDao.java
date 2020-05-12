package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.CommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.RootComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 一级评论表(RootComment)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Repository
public interface RootCommentDao extends BaseMapper<RootComment> {

    Integer getCount(@Param("host_id") Long hostId, @Param("type") Integer type);
    List<Long> getIdLists(@Param("host_id") Long hostId, @Param("type") Integer type);
    IPage<CommentVO> getCommentPageByType(Page<?> page, @Param("host_id") Long hostId, @Param("type") Integer type);
}