package cn.novalue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ResumeGeneralDao;
import cn.novalue.blog.model.entity.ResumeGeneral;
import cn.novalue.blog.service.ResumeGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人简历常规信息(ResumeGeneral)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
@Service("resumeGeneralService")
public class ResumeGeneralServiceImpl extends ServiceImpl<ResumeGeneralDao, ResumeGeneral> implements ResumeGeneralService {

    @Autowired
    private ResumeGeneralDao resumeGeneralDao;

    @Override
    public ResumeGeneral findByUserId(Long userId) {
        return resumeGeneralDao.findByUserId(userId);
    }
}