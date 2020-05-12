package cn.novalue.blog.model.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Wu Yangjie
 * @date 2020-05-10
 */
@Data
public class ChildCommentParam {
    @NotBlank(message = "评论内容不能为空")
    private String content;
    @NotNull(message = "一级评论id不能为空")
    private Long parentId;
}
