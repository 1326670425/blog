package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.params.RegisterParam;
import cn.novalue.blog.model.params.ResetPwdParam;
import cn.novalue.blog.model.support.CheckCode;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.ArticleVO;
import cn.novalue.blog.model.vo.CommentVO;
import cn.novalue.blog.model.vo.MessageVO;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.service.*;
import cn.novalue.blog.utils.MyBeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private MailService mailService;
    @Autowired
    private HttpSession session;

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

    @GetMapping("forget/info")
    public Response getInfo(@RequestParam("info") String info) {
        User u = userService.selectOne(info);
        if (u == null)
            return Response.failure(400, "未找到用户");
        if (StringUtils.isEmpty(u.getEmail()))
            return Response.failure(400, "用户未绑定邮箱");
        session.setAttribute("info", u.getEmail());
        // 返回脱敏后的邮箱
        return Response.success("ok", u.getEmail().replaceAll("(\\w{3})\\w+@(\\w+)", "$1****@$2"));
    }
    @GetMapping("forget/code")
    public void getCheckCode() {
        String to = (String) session.getAttribute("info");
        Map<String, Object> model = new HashMap<>();
        CheckCode code = CheckCode.create();
        model.put("info", to);
        model.put("code", code.getCode());
        session.setAttribute("code", code);
        mailService.sendTemplateMail(to, "找回密码", model, "mail.ftl", null);
    }
    @GetMapping("forget/verify")
    public Response verifyCheckCode(@RequestParam("code") String code) {
        CheckCode checkCode = (CheckCode) session.getAttribute("code");
        Assert.notNull(checkCode, "尚未发送验证码");
        if (CheckCode.isExpired(checkCode) || !checkCode.getCode().equals(code))
            return Response.failure(400, "验证码错误或过期");
        session.setAttribute("validated", true);
        return Response.success();
    }
    @PostMapping("forget/reset")
    public Response resetPassword(@RequestBody @Validated ResetPwdParam pwdParam) {
        Boolean status = (Boolean) session.getAttribute("validated");
        if (status == null || !status)
            return Response.failure(400, "尚未验证");
        String info = (String) session.getAttribute("info");
        User user = userService.selectOne(info);
        user.setPassword(new BCryptPasswordEncoder().encode(pwdParam.getPassword()));
        userService.updateById(user);
        session.removeAttribute("info");
        session.removeAttribute("code");
        session.removeAttribute("validated");
        return Response.success();
    }
    @GetMapping("search/user")
    public Response register(@RequestParam String username) {
        User user =  userService.findByUsername(username);
        if(Objects.isNull(user)){
            return Response.failure(HttpStatus.BAD_REQUEST.value(), "您查找的人不存在");
        }
        else {
            UserVO userVO = new UserVO();
            MyBeanUtils.copy(user,userVO);
            return Response.success(userVO);
        }
    }
}
