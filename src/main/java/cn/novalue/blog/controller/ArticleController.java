package cn.novalue.blog.controller;



import cn.novalue.blog.model.entity.Article;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.ArticleService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文章表(Article)表控制层
 *
 * @author Wu yangjie
 * @date 2020-05-17
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Autowired
    private ArticleService articleService;

    @PostMapping("add")
    public Response add(@RequestBody Article article) {
        if (article.getUserId() == null)
            article.setUserId(SecurityUtils.getUser().getId());
        else if (!article.getUserId().equals(SecurityUtils.getUser().getId()))
            return Response.failure(400, "不能修改别人的内容");
        articleService.saveOrUpdate(article);
        return Response.success("保存成功");
    }

    @PostMapping("delete")
    public Response delete(@RequestBody Article article) {
        if (!article.getUserId().equals(SecurityUtils.getUser().getId()))
            return Response.failure(400, "不能删除别人的内容");
        articleService.removeArticle(article.getId());
        return Response.success("删除成功");
    }
}