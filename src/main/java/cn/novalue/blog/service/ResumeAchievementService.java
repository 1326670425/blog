package cn.novalue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.ResumeAchievement;

import java.util.List;
import java.util.Map;

/**
 * 个人荣誉(ResumeAchievement)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
public interface ResumeAchievementService extends IService<ResumeAchievement> {
    Map<String, List<ResumeAchievement>> findByUserId(Long userId);
}