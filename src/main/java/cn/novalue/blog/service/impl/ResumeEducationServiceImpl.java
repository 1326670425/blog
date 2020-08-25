package cn.novalue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ResumeEducationDao;
import cn.novalue.blog.model.entity.ResumeEducation;
import cn.novalue.blog.service.ResumeEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教育经历(ResumeEducation)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Service("resumeEducationService")
public class ResumeEducationServiceImpl extends ServiceImpl<ResumeEducationDao, ResumeEducation> implements ResumeEducationService {

    @Autowired
    private ResumeEducationDao resumeEducationDao;

    @Override
    public List<ResumeEducation> findByUserId(Long userId) {
        return resumeEducationDao.findByUserId(userId);
    }
}