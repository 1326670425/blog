package cn.novalue.blog.service.impl;

import cn.novalue.blog.event.U2uNotifyEvent;
import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.LetterVO;
import cn.novalue.blog.service.UserService;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.LetterDao;
import cn.novalue.blog.model.entity.Letter;
import cn.novalue.blog.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 留言表(Letter)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-06-19
 */
@Service("letterService")
public class LetterServiceImpl extends ServiceImpl<LetterDao, Letter> implements LetterService {

    @Autowired
    private LetterDao letterDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void add(Letter letter) {
        User currentUser = SecurityUtils.getUser();
        letter.setSender(currentUser.getId());
        Long receiver = letter.getReceiver();
        if (userService.getById(receiver) == null)
            throw new RuntimeException("找不到用户");
        // 保存留言
        save(letter);
        // 给接收者发送通知
        U2uNotify notify = new U2uNotify();
        notify.setType(U2uNotifyType.LETTER.name());
        notify.setTargetId(receiver);
        notify.setTargetType("user");
        notify.setMessage(letter.getContent().substring(0, 256));
        eventPublisher.publishEvent(new U2uNotifyEvent(notify, currentUser));
    }

    @Override
    public IPage<LetterVO> getLetterByPage(Page page, Long userId) {
        return letterDao.getLetterByPage(page, userId);
    }

    /* 处理留言通知 */
    @Override
    public Response handle(U2uNotify u2uNotify) {
        return Response.success();
    }

    @Override
    public U2uNotifyType getHandlerType() {
        return U2uNotifyType.LETTER;
    }
}