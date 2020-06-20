package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.vo.LetterVO;
import cn.novalue.blog.service.UserService;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.LetterDao;
import cn.novalue.blog.model.entity.Letter;
import cn.novalue.blog.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 留言表(Letter)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-06-19
 */
@Service("letterService")
public class LetterServiceImpl extends ServiceImpl<LetterDao, Letter> implements LetterService {

    @Autowired
    private LetterDao letterDao;
    @Autowired
    private UserService userService;

    @Override
    public void add(Letter letter) {
        letter.setSender(SecurityUtils.getUser().getId());
        Long receiver = letter.getReceiver();
        if (userService.getById(receiver) == null)
            throw new RuntimeException("找不到用户");
        save(letter);
        // 这里留着给接收者发通知
    }

    @Override
    public IPage<LetterVO> getLetterByPage(Page page, Long userId) {
        return letterDao.getLetterByPage(page, userId);
    }
}