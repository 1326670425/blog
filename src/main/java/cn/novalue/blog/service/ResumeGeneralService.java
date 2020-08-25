package cn.novalue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.ResumeGeneral;

/**
 * 个人简历常规信息(ResumeGeneral)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-08-24
 */
public interface ResumeGeneralService extends IService<ResumeGeneral> {
    ResumeGeneral findByUserId(Long userId);
}