package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.MessageVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.Message;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 短消息表(Message)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-05-09
 */
@Repository
public interface MessageDao extends BaseMapper<Message> {
    IPage<MessageVO> getMessageByPage(Page<?> page);
    IPage<MessageVO> getUserMessageByPage(Page<?> page, @Param("userId") Long userId);
}