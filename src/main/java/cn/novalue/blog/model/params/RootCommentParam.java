package cn.novalue.blog.model.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评论参数
 *
 * @author Wu Yangjie
 * @date 2020-05-10
 */
@Data
public class RootCommentParam {
    @NotBlank(message = "评论内容不能为空")
    private String content;
    @NotNull(message = "所评内容不存在")
    private Long hostId;
    @NotNull(message = "所评内容类型不能为空，0：短消息，1：文章")
    private Integer type;
}
