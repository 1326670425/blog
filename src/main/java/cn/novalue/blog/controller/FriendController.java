package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.service.FriendService;
import cn.novalue.blog.service.U2uNotifyService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 好友表(Friend)表控制层
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    /**
     * 服务对象
     */
    @Autowired
    private FriendService friendService;
    @Autowired
    private U2uNotifyService u2uNotifyService;

    @PostMapping("request")
    public Response Request(@RequestBody U2uNotify friendRequest) {
        u2uNotifyService.save(friendRequest);
        return Response.success("好友申请提交成功");
    }

    @PostMapping("handleRequest")
    public void handleRequest(@RequestBody U2uNotify friendRequest) {
        u2uNotifyService.handleU2uNotify(friendRequest);
    }

    @GetMapping("getFriend")
    public List<UserVO> getFriends(
            @RequestParam(value = "group", required = false) String group) {
        Long currentUserId = SecurityUtils.getUser().getId();
        return friendService.getFriends(currentUserId, group);
    }
}