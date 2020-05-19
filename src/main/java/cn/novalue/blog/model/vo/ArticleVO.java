package cn.novalue.blog.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yangjie
 * @date 2020-05-17
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ArticleVO extends MessageVO {
    private String title;
    private String description;
    private Integer type;
    private Integer status;
}
