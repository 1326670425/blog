package cn.novalue.blog.dao;

import cn.novalue.blog.model.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.novalue.blog.model.entity.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author Wu yangjie
 * @date 2020-05-17
 */
@Repository
public interface ArticleDao extends BaseMapper<Article> {
    IPage<ArticleVO> getArticleByPage(Page<?> page, @Param("userId") Long userId);
    IPage<ArticleVO> getMyArticleByPage(Page<?> page, @Param("userId") Long userId);
    ArticleVO getDetails(@Param("id") Long id);
}