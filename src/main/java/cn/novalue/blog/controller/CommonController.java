package cn.novalue.blog.controller;

import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.params.RegisterParam;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.ArticleVO;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.model.vo.MessageVO;
import cn.novalue.blog.service.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private ChildCommentService childCommentService;

    @PostMapping("register")
    public Response register(@RequestBody @Validated RegisterParam registerParam) {
        boolean isSuccess = userService.register(registerParam.getUsername(), registerParam.getPassword());
        if (isSuccess)
            return Response.success("注册成功");
        else
            return Response.failure(400, "注册失败");
    }

    @GetMapping("message")
    public IPage<MessageVO> getMessageByPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return messageService.getMessageByPage(new Page<>(page, size), null);
    }

    @GetMapping("message/user/{userId}")
    public IPage<MessageVO> getUserMessageByPage(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return messageService.getMessageByPage(new Page<>(page, size), userId);
    }
    @GetMapping("article")
    public IPage<ArticleVO> getArticleByPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return articleService.getArticleByPage(new Page<>(page, size), null);
    }

    @GetMapping("article/user/{userId}")
    public IPage<ArticleVO> getUserArticleByPage(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return articleService.getArticleByPage(new Page<>(page, size), userId);
    }
    @GetMapping("article/{id}")
    public ArticleVO getDetails(@PathVariable("id") Long id) {
        return articleService.getDetails(id);
    }
    @GetMapping("rootComment")
    public IPage<CommentVO> getRootCommentByPage(
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "hostId") Long hostId,
            @RequestParam(value = "order", required = false, defaultValue = "") String orderItem,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return rootCommentService.getCommentPageByType(new Page(page, size), orderItem, hostId, CommentType.valueOf(type));
    }

    @GetMapping("childComment")
    public IPage<CommentVO> getChildCommentByPage(
            @RequestParam(value = "parentId") Long parentId,
            @RequestParam(value = "order", required = false, defaultValue = "") String orderItem,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return childCommentService.getCommentPage(new Page(page, size), orderItem, parentId);
    }
}
