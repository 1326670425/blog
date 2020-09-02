package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.Question;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.QuestionService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题表(Question)表控制层
 *
 * @author makejava
 * @since 2020-09-01 21:36:16
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("add")
    public Response add(@RequestBody Question question){
        if (question.getUserId() == null)
            question.setUserId(SecurityUtils.getUser().getId());
        questionService.saveOrUpdate(question);
        return Response.success("密保问题保存成功");
    }

    @GetMapping("selectOne")
    public Question selectOne(Long id) {
        return this.questionService.queryById(id);
    }

}