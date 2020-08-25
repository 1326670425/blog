package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.*;
import cn.novalue.blog.service.*;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Yangjie
 * @date 2020-08-24
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserService userService;

    @GetMapping("/general/{id}")
    public ResumeGeneral getGeneral(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return resumeService.getGeneral(user);
    }
    @GetMapping("/education/{id}")
    public List<ResumeEducation> getEducations(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return resumeService.getEducation(user);
    }
    @GetMapping("/work/{id}")
    public List<ResumeWork> getWorks(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return resumeService.getWorks(user);
    }
    @GetMapping("/achievement/{id}")
    public Map<String, List<ResumeAchievement>> getAchievements(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return resumeService.getAchievements(user);
    }
    @PostMapping("/general/add")
    public void addGeneral(@RequestBody ResumeGeneral general) {
        User user = SecurityUtils.getUser();
        Assert.notNull(user, "未登录");
        general.setUserId(user.getId());
        resumeService.saveGeneral(general);
    }
    @PostMapping("/education/add")
    public void addEducations(@RequestBody ResumeEducation education) {
        User user = SecurityUtils.getUser();
        Assert.notNull(user, "未登录");
        education.setUserId(user.getId());
        resumeService.saveEducation(Collections.singletonList(education));
    }
    @PostMapping("/work/add")
    public void addWorks(@RequestBody ResumeWork work) {
        User user = SecurityUtils.getUser();
        Assert.notNull(user, "未登录");
        work.setUserId(user.getId());
        resumeService.saveWork(Collections.singletonList(work));
    }
    @PostMapping("/achievement/add")
    public void getEducations(@RequestBody ResumeAchievement achievement) {
        User user = SecurityUtils.getUser();
        Assert.notNull(user, "未登录");
        achievement.setUserId(user.getId());
        resumeService.saveAchievement(Collections.singletonList(achievement));
    }
}
