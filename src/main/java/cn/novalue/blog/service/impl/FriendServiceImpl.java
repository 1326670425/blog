package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.U2uNotify;
import cn.novalue.blog.model.enums.U2uNotifyType;
import cn.novalue.blog.model.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.FriendDao;
import cn.novalue.blog.model.entity.Friend;
import cn.novalue.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean addFriend(@NotNull Long userId1, @NotNull Long userId2) {
        if (isExist(userId1, userId2))
            return false;
        // 保证userId1小于userId2，防止出现重复
        if (userId1 > userId2) {
            long i = userId1;
            userId1 = userId2;
            userId2 = i;
        }
        Friend friend = new Friend();
        friend.setUserId1(userId1);
        friend.setUserId2(userId2);
        boolean result = save(friend);
        // saveToRedis(friend);
        return result;
    }

    @Override
    public List<UserVO> getFriends(Long currentUserId, String group) {
        if (StringUtils.isEmpty(group.trim()))
            group = null;
        return friendDao.getFriends(currentUserId, group);
    }
    // 检查是否已经存在好友关系
    private boolean isExist(Long userId1, Long userId2) {
        Boolean exist = redisTemplate.opsForSet().isMember("friend:"+userId1+":默认分组", userId2);
        if (exist == null || !exist)
            exist = redisTemplate.opsForSet().isMember("friend:"+userId2+":默认分组", userId1);
        return exist != null && exist;
    }
    // 好友关系保存到Redis
    private void saveToRedis(Friend friend) {
        Long userId1 = friend.getUserId1();
        Long userId2 = friend.getUserId2();
        redisTemplate.opsForSet().add("friend:"+userId1+":默认分组", String.valueOf(userId2));
        redisTemplate.opsForSet().add("friend:"+userId2+":默认分组", String.valueOf(userId1));
    }


    /* 处理好友通知 */
    @Override
    public Boolean handle(U2uNotify u2uNotify) {
        int status = u2uNotify.getStatus();
        boolean result = false;
        // 同意好友申请
        if (status == 1) {
            Long userId1 = u2uNotify.getSender();
            Long userId2 = u2uNotify.getReceiver();

            result = addFriend(userId1, userId2);
        }
        return result;
    }

    @Override
    public U2uNotifyType getHandlerType() {
        return U2uNotifyType.FRIEND;
    }
}