package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题表(Question)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-01 21:36:16
 */
public interface QuestionDao extends BaseMapper<Question> {

    Question queryById(Long id);

    List<Question> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Question> queryAll(Question question);

    int insert(Question question);

    int update(Question question);


    int deleteById(Long id);

    List<Question> find(@Param("userId") Long userId);
}