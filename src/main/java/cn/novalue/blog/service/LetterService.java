package cn.novalue.blog.service;

import cn.novalue.blog.handler.U2uNotifyHandler;
import cn.novalue.blog.model.vo.LetterVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.Letter;

/**
 * 留言表(Letter)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-06-19
 */
public interface LetterService extends IService<Letter>, U2uNotifyHandler {
    void add(Letter letter);
    IPage<LetterVO> getLetterByPage(Page page, Long userId);
}