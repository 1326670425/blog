package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.AchievementType;
import cn.novalue.blog.service.AchievementTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ResumeAchievementDao;
import cn.novalue.blog.model.entity.ResumeAchievement;
import cn.novalue.blog.service.ResumeAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人荣誉(ResumeAchievement)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Service("resumeAchievementService")
public class ResumeAchievementServiceImpl extends ServiceImpl<ResumeAchievementDao, ResumeAchievement> implements ResumeAchievementService {

    @Autowired
    private ResumeAchievementDao resumeAchievementDao;
    @Autowired
    private AchievementTypeService achievementTypeService;

    @Override
    public Map<String, List<ResumeAchievement>> findByUserId(Long userId) {
        List<AchievementType> types = achievementTypeService.list();
        Map<String, List<ResumeAchievement>> map = new HashMap<>();
        for (AchievementType type : types) {
            List<ResumeAchievement> list = resumeAchievementDao.findByUserIdAndType(userId, type.getId());
            map.put(type.getTypeName(), list);
        }
        return map;
    }
}