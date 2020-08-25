package cn.novalue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.ResumeWork;

import java.util.List;

/**
 * 工作经历(ResumeWork)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
public interface ResumeWorkService extends IService<ResumeWork> {
    List<ResumeWork> findByUserId(Long userId);
}