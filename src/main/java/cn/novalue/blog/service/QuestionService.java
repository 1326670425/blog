package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 问题表(Question)表服务接口
 *
 * @author makejava
 * @since 2020-09-01 21:36:16
 */
public interface QuestionService extends IService<Question> {

    Question queryById(Long id);

    List<Question> queryAllByLimit(int offset, int limit);

    Question insert(Question question);

    Question update(Question question);

    boolean deleteById(Long id);

    List<Question> find(Long userId);
}