package cn.novalue.blog.service;

import cn.novalue.blog.model.vo.ArticleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.novalue.blog.model.entity.Article;

/**
 * 文章表(Article)表服务接口
 *
 * @author Wu yangjie
 * @date 2020-05-17
 */
public interface ArticleService extends IService<Article> {
    IPage<ArticleVO> getArticleByPage(Page<?> page, Long userId);
    boolean removeArticle(Long id);
    ArticleVO getDetails(Long id);
}