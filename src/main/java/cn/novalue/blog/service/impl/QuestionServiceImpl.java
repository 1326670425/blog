package cn.novalue.blog.service.impl;

import cn.novalue.blog.dao.QuestionDao;
import cn.novalue.blog.model.entity.Question;
import cn.novalue.blog.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题表(Question)表服务实现类
 *
 * @author makejava
 * @since 2020-09-01 21:36:16
 */
@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao,Question> implements QuestionService {
    @Resource
    private QuestionDao questionDao;

    @Override
    public Question queryById(Long id) {
        return this.questionDao.queryById(id);
    }

    @Override
    public List<Question> queryAllByLimit(int offset, int limit) {
        return this.questionDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Question insert(Question question) {
        this.questionDao.insert(question);
        return question;
    }

    @Override
    public Question update(Question question) {
        this.questionDao.update(question);
        return this.queryById(question.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return this.questionDao.deleteById(id) > 0;
    }

    @Override
    public List<Question> find(Long userId) {

        return questionDao.find(userId);
    }
}