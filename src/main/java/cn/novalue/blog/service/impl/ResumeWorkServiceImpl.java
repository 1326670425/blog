package cn.novalue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ResumeWorkDao;
import cn.novalue.blog.model.entity.ResumeWork;
import cn.novalue.blog.service.ResumeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作经历(ResumeWork)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Service("resumeWorkService")
public class ResumeWorkServiceImpl extends ServiceImpl<ResumeWorkDao, ResumeWork> implements ResumeWorkService {

    @Autowired
    private ResumeWorkDao resumeWorkDao;

    @Override
    public List<ResumeWork> findByUserId(Long userId) {
        return resumeWorkDao.findByUserId(userId);
    }
}