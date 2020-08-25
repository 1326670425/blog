package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.*;
import cn.novalue.blog.service.*;
import cn.novalue.blog.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Wu Yangjie
 * @date 2020-08-24
 */
@Slf4j
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeGeneralService resumeGeneralService;
    @Autowired
    private ResumeEducationService resumeEducationService;
    @Autowired
    private ResumeWorkService resumeWorkService;
    @Autowired
    private ResumeAchievementService resumeAchievementService;

    @Override
    public void saveGeneral(ResumeGeneral general) {
        if (Objects.isNull(general.getUserId()))
            general.setUserId(SecurityUtils.getUser().getId());
        resumeGeneralService.saveOrUpdate(general);
    }

    @Override
    public ResumeGeneral getGeneral(User user) {
        ResumeGeneral general = resumeGeneralService.findByUserId(user.getId());
        // 加隐私校验
        return general;
    }

    @Override
    public void saveEducation(List<ResumeEducation> educations) {
        Long currentUserId = SecurityUtils.getUser().getId();
        for (ResumeEducation education : educations) {
            if (Objects.isNull(education.getUserId()))
                education.setUserId(currentUserId);
        }
        resumeEducationService.saveOrUpdateBatch(educations);
    }

    @Override
    public List<ResumeEducation> getEducation(User user) {
        List<ResumeEducation> educations = resumeEducationService.findByUserId(user.getId());
        // 加隐私校验
        return educations;
    }

    @Override
    public void saveWork(List<ResumeWork> works) {
        Long currentUserId = SecurityUtils.getUser().getId();
        for (ResumeWork work : works) {
            if (Objects.isNull(work.getUserId()))
                work.setUserId(currentUserId);
        }
        resumeWorkService.saveOrUpdateBatch(works);
    }

    @Override
    public List<ResumeWork> getWorks(User user) {
        List<ResumeWork> works = resumeWorkService.findByUserId(user.getId());
        return works;
    }

    @Override
    public void saveAchievement(List<ResumeAchievement> achievements) {
        Long currentUserId = SecurityUtils.getUser().getId();
        for (ResumeAchievement achievement : achievements) {
            if (Objects.isNull(achievement.getUserId()))
                achievement.setUserId(currentUserId);
        }
        resumeAchievementService.saveOrUpdateBatch(achievements);
    }

    @Override
    public Map<String, List<ResumeAchievement>> getAchievements(User user) {
        Map<String, List<ResumeAchievement>> achievements = resumeAchievementService.findByUserId(user.getId());
        return achievements;
    }
}
