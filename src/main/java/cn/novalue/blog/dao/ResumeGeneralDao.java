package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.ResumeAchievement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.ResumeGeneral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 个人简历常规信息(ResumeGeneral)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Repository
public interface ResumeGeneralDao extends BaseMapper<ResumeGeneral> {
    ResumeGeneral findByUserId(@Param("userId") Long userId);
}