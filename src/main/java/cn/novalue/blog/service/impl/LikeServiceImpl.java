package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.entity.*;
import cn.novalue.blog.model.params.LikeParam;
import cn.novalue.blog.model.vo.BaseContentVO;
import cn.novalue.blog.service.*;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Wu Yangjie
 * @date 2020-06-09
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private ChildCommentService childCommentService;

    @Override
    public void handleVO(BaseContentVO baseContentVO, Integer type) {

        Long voId = baseContentVO.getId();
        baseContentVO.setLikeNum(getCount(type, voId));
        if (SecurityUtils.isAuthenticated()) {
            Long currentUser = SecurityUtils.getUser().getId();
            baseContentVO.setLiked(isLiked(type, voId, currentUser));
        }

    }

    @Override
    public Boolean handleRequest(LikeParam likeParam) {
        Integer type = likeParam.getType();
        Long hostId = likeParam.getHostId();
        Boolean like = likeParam.getLike();
        Long userId = SecurityUtils.getUser().getId();
        Boolean result;
        String sql;
        if (like && !isLiked(type, hostId, userId)) {
            stringRedisTemplate.opsForSet().add("like:"+type+":"+hostId, String.valueOf(userId));
            result = Boolean.TRUE;
            sql = "like_num = like_num + 1";
        } else {
            stringRedisTemplate.opsForSet().remove("like:"+type+":"+hostId, String.valueOf(userId));
            result = Boolean.FALSE;
            sql = "like_num = like_num - 1";
        }
        // 点赞人数持久化方案2：放到关系数据库，有点丑。
        switch (type) {
            case 0:
                Message message = new Message();
                message.setId(hostId);
                messageService.update(new UpdateWrapper<>(message).setSql(true, sql));
                break;
            case 1:
                Article article = new Article();
                article.setId(hostId);
                articleService.update(new UpdateWrapper<>(article).setSql(true, sql));
                break;
            case 2:
                RootComment rootComment = new RootComment();
                rootComment.setId(hostId);
                rootCommentService.update(new UpdateWrapper<>(rootComment).setSql(true, sql));
                break;
            case 3:
                ChildComment childComment = new ChildComment();
                childComment.setId(hostId);
                childCommentService.update(new UpdateWrapper<>(childComment).setSql(true, sql));
                break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }


    private Boolean isLiked(Integer type, Long hostId, Long userId) {
        return stringRedisTemplate.opsForSet().isMember("like:"+type+":"+hostId, String.valueOf(userId));
    }


    private Integer getCount(Integer type, Long hostId) {
        Long count = stringRedisTemplate.opsForSet().size("like:"+type+":"+hostId);
        return Optional.ofNullable(count).orElse(0L).intValue();
    }
}
