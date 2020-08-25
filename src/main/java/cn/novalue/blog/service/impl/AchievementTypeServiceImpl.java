package cn.novalue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.AchievementTypeDao;
import cn.novalue.blog.model.entity.AchievementType;
import cn.novalue.blog.service.AchievementTypeService;
import org.springframework.stereotype.Service;

/**
 * 荣誉类型(AchievementType)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-08-25
 */
@Service("achievementTypeService")
public class AchievementTypeServiceImpl extends ServiceImpl<AchievementTypeDao, AchievementType> implements AchievementTypeService {

}