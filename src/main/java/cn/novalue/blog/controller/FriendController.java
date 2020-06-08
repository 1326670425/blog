package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.NotifyFriendRequest;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.UserVO;
import cn.novalue.blog.service.FriendService;
import cn.novalue.blog.service.NotifyFriendRequestService;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    private NotifyFriendRequestService friendRequestService;

    @PostMapping("request")
    public Response Request(@RequestBody NotifyFriendRequest friendRequest) {
        friendRequestService.save(friendRequest);
        return Response.success("好友申请提交成功");
    }

    @PostMapping("handleRequest")
    public void handleRequest(@RequestBody NotifyFriendRequest friendRequest) {
        friendRequestService.handleRequest(friendRequest);
    }

    @GetMapping("getFriend")
    public IPage<UserVO> getFriends(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "group", required = false) String group) {
        Long currentUserId = SecurityUtils.getUser().getId();
        return friendService.getFriends(new Page<>(page, size), currentUserId, group);
    }
}