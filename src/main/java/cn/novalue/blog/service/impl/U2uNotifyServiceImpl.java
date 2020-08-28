package cn.novalue.blog.service.impl;

import cn.novalue.blog.factory.U2uNotifyFactory;
import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.entity.User;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.U2uNotifyDao;
import cn.novalue.blog.service.U2uNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户对用户通知表(U2uNotify)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-07-04
 */
@Service("u2uNotifyService")
public class U2uNotifyServiceImpl extends ServiceImpl<U2uNotifyDao, U2uNotify> implements U2uNotifyService {

    @Autowired
    private U2uNotifyDao u2uNotifyDao;
    @Autowired
    private U2uNotifyFactory u2uNotifyFactory;

    @Override
    public Integer getCount() {
        User currentUser = SecurityUtils.getUser();
        return u2uNotifyDao.getCount(currentUser.getId());
    }

    @Override
    public Map<String, Integer> getTypeCount() {
        User currentUser = SecurityUtils.getUser();
        return u2uNotifyDao.getTypeCount(currentUser.getId());
    }

    @Override
    public List<U2uNotify> getNotifyByType(U2uNotifyType notifyType) {
        User currentUser = SecurityUtils.getUser();
        return u2uNotifyDao.getNotifyByType(currentUser.getId(), notifyType.name().toLowerCase());
    }

    @Override
    public Response handleU2uNotify(U2uNotify u2uNotify) {
        U2uNotifyType u2uNotifyType;
        try {
            System.out.println(u2uNotify);
            u2uNotifyType = U2uNotifyType.valueOf(u2uNotify.getType().toUpperCase());
            Response result = u2uNotifyFactory.getHandler(u2uNotifyType).handle(u2uNotify);
            // 标记已读
            u2uNotify.setStatus(1);
            updateById(u2uNotify);
            return result;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("通知类型不存在");
        }
    }
}