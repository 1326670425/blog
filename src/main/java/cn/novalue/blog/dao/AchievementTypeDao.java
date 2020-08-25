package cn.novalue.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.AchievementType;
import org.springframework.stereotype.Repository;

/**
 * 荣誉类型(AchievementType)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-08-25
 */
@Repository
public interface AchievementTypeDao extends BaseMapper<AchievementType> {

}