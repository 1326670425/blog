package cn.novalue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.ResumeEducation;

import java.util.List;

/**
 * 教育经历(ResumeEducation)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
public interface ResumeEducationService extends IService<ResumeEducation> {
    List<ResumeEducation> findByUserId(Long userId);
}