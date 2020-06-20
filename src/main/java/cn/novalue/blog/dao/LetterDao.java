package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.LetterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.Letter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 留言表(Letter)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-06-19
 */
@Repository
public interface LetterDao extends BaseMapper<Letter> {
    IPage<LetterVO> getLetterByPage(Page<?> page, @Param("userId") Long userId);
}