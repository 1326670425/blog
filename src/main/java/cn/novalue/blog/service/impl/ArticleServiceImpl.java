package cn.novalue.blog.service.impl;

import cn.novalue.blog.model.enums.CommentType;
import cn.novalue.blog.model.vo.ArticleVO;
import cn.novalue.blog.service.ChildCommentService;
import cn.novalue.blog.service.RootCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.novalue.blog.dao.ArticleDao;
import cn.novalue.blog.model.entity.Article;
import cn.novalue.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Wu yangjie
 * @date 2020-05-17
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RootCommentService rootCommentService;
    @Autowired
    private ChildCommentService childCommentService;


    @Override
    public IPage<ArticleVO> getArticleByPage(Page<?> page, Long userId) {
        IPage<ArticleVO> pageMessage = articleDao.getArticleByPage(page, userId);
        List<ArticleVO> articles = pageMessage.getRecords();
        for (ArticleVO articleVO : articles) {
            articleVO.setCommentNum(rootCommentService.getCount(articleVO.getId(), CommentType.ARTICLE));
        }
        pageMessage.setRecords(articles);
        return pageMessage;
    }

    @Override
    public boolean removeArticle(Long id) {
        boolean flag = removeById(id);
        List<Long> rootCommentIds = rootCommentService.getIdLists(id, CommentType.ARTICLE);
        flag &= rootCommentService.removeByIds(rootCommentIds);
        flag &= childCommentService.removeByParents(rootCommentIds);
        return flag;
    }

    @Override
    public ArticleVO getDetails(Long id) {
        ArticleVO details = articleDao.getDetails(id);
        details.setCommentNum(rootCommentService.getCount(details.getId(), CommentType.ARTICLE));
        return details;
    }
}