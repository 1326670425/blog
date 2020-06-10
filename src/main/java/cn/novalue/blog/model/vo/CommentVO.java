package cn.novalue.blog.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentVO extends BaseContentVO{
    private Integer childrenNum;
}
