package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.*;

import java.util.List;
import java.util.Map;

public interface ResumeService {
    void saveGeneral(ResumeGeneral general);
    ResumeGeneral getGeneral(User user);

    void saveEducation(List<ResumeEducation> educations);
    List<ResumeEducation> getEducation(User user);

    void saveWork(List<ResumeWork> works);
    List<ResumeWork> getWorks(User user);

    void saveAchievement(List<ResumeAchievement> achievements);
    Map<String, List<ResumeAchievement>> getAchievements(User user);
}
