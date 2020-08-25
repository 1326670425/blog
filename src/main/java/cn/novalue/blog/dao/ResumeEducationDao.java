package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.ResumeAchievement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.ResumeEducation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教育经历(ResumeEducation)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Repository
public interface ResumeEducationDao extends BaseMapper<ResumeEducation> {
    List<ResumeEducation> findByUserId(@Param("userId") Long userId);
}