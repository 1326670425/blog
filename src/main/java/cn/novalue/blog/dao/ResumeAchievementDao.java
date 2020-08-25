package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.ResumeWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.ResumeAchievement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 个人荣誉(ResumeAchievement)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Repository
public interface ResumeAchievementDao extends BaseMapper<ResumeAchievement> {
    List<ResumeAchievement> findByUserIdAndType(@Param("userId") Long userId, @Param("typeId") Long typeId);
}