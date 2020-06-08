package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.FriendDao;
import cn.novalue.blog.model.entity.Friend;
import cn.novalue.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 好友表(Friend)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-06-08
 */
@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendDao, Friend> implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Override
    public IPage<UserVO> getFriends(Page page, Long currentUserId, String group) {

        return friendDao.getFriends(page, currentUserId, group);
    }
}