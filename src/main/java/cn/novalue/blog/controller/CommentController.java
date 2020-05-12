package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.ChildComment;
import cn.novalue.blog.model.entity.RootComment;
import cn.novalue.blog.model.params.ChildCommentParam;
import cn.novalue.blog.model.params.RootCommentParam;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.ChildCommentService;
import cn.novalue.blog.service.RootCommentService;
import cn.novalue.blog.utils.MyBeanUtils;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 评论控制器
 *
 * @author Wu Yangjie
 * @date 2020-05-10
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private ChildCommentService childCommentService;

    @PostMapping("root/add")
    public Response addRoot(@RequestBody @Validated RootCommentParam commentParam) {
        RootComment comment = new RootComment();
        System.out.println(commentParam);
        MyBeanUtils.copy(commentParam, comment);
        comment.setUserId(SecurityUtils.getUser().getId());
        System.out.println(comment);
        if (rootCommentService.add(comment))
            return Response.success("发表评论成功");
        return Response.failure(400, "发表评论失败");
    }

    @PostMapping("child/add")
    public Response addChild(@RequestBody @Validated ChildCommentParam commentParam) {
        ChildComment comment = new ChildComment();
        MyBeanUtils.copy(commentParam, comment);
        comment.setUserId(SecurityUtils.getUser().getId());
        if (childCommentService.add(comment))
            return Response.success("回复成功");
        return Response.failure(400, "回复失败");
    }

}
