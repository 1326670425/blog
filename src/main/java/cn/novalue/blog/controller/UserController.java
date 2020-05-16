package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.service.FileService;
import cn.novalue.blog.service.UserService;
import cn.novalue.blog.utils.MyBeanUtils;
import cn.novalue.blog.utils.SecurityUtils;
import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
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

    @GetMapping("")
    public UserVO getProfile() {
        User user = userService.getById(SecurityUtils.getUser().getId());
        UserVO userVo = new UserVO();
        MyBeanUtils.copy(user, userVo);
        return userVo;
    }

    @PostMapping("update")
    public Response update(@RequestBody UserVO userVO) {
        User user = SecurityUtils.getUser();
        if (!user.getId().equals(userVO.getId()))
            return Response.failure(400, "不能修改id");
        MyBeanUtils.copy(userVO, user);
        userService.updateById(user);
        return Response.success();
    }

    @PostMapping("uploadAvatar")
    public Response uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String path = fileService.upload(file);
        return null;
    }
}