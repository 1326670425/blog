package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.service.FileService;
import cn.novalue.blog.service.U2uNotifyService;
import cn.novalue.blog.service.UserService;
import cn.novalue.blog.utils.MyBeanUtils;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户表(User)表控制层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private U2uNotifyService u2uNotifyService;

    @GetMapping("{userId}")
    public UserVO getProfile(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        UserVO userVo = new UserVO();
        MyBeanUtils.copy(user, userVo);
        return userVo;
    }

    @PostMapping("update")
    public Response update(@RequestBody UserVO userVO) {
        User user = SecurityUtils.getUser();
        if (userVO.getId() != null && !user.getId().equals(userVO.getId()))
            return Response.failure(400, "不能修改id");
        MyBeanUtils.copy(userVO, user);
        userService.updateById(user);
        return Response.success();
    }

    @PostMapping("uploadAvatar")
    public Response uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String path = fileService.upload(file, request);
        User user = SecurityUtils.getUser();
        user.setAvatar(path);
        userService.updateById(user);
        return Response.success("上传成功", path);
    }

    @PostMapping("handleU2uNotify")
    public void handleRequest(@RequestBody U2uNotify notify) {
        u2uNotifyService.handleU2uNotify(notify);
    }
}