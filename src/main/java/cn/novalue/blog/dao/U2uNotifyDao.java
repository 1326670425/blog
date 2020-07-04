package cn.novalue.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.U2uNotify;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户对用户通知表(U2uNotify)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-07-04
 */
@Repository
public interface U2uNotifyDao extends BaseMapper<U2uNotify> {
    Integer getCount(@Param("receiver") Long receiver);
    Map<String, Integer> getTypeCount(@Param("receiver") Long receiver);
    List<U2uNotify> getNotifyByType(@Param("receiver") Long receiver, @Param("type") String type);
}