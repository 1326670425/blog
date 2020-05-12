package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.Message;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.MessageService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 短消息表(Message)表控制层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Autowired
    private MessageService messageService;

    @PostMapping("add")
    public Response add(@RequestBody Message message) {
        message.setUserId(SecurityUtils.getUser().getId());
        messageService.save(message);
        return Response.success("发表成功");
    }

    @PostMapping("edit")
    public Response edit(@RequestBody Message message) {
        if (!message.getUserId().equals(SecurityUtils.getUser().getId()))
            return Response.failure(400, "不能修改别人的内容");
        messageService.saveOrUpdate(message);
        return Response.success("修改成功");
    }

    @PostMapping("delete")
    public Response delete(@RequestBody Message message) {
        if (!message.getUserId().equals(SecurityUtils.getUser().getId()))
            return Response.failure(400, "不能删除别人的内容");
        messageService.removeMessage(message.getId());
        return Response.success("删除成功");
    }
}