package cn.novalue.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.ResumeWork;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工作经历(ResumeWork)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Repository
public interface ResumeWorkDao extends BaseMapper<ResumeWork> {
    List<ResumeWork> findByUserId(@Param("userId") Long userId);
}