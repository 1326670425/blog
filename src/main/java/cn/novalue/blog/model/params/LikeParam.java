package cn.novalue.blog.model.params;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 点赞操作参数
 *
 * @author Wu Yangjie
 * @date 2020-06-09
 */
@Data
public class LikeParam {
    @NotNull(message = "点赞内容类型不能为空")
    // 目前只有四个类型
    // 0：message，1：article，2：root comment，3：child comment
    @Min(value = 0, message = "类型超限")
    @Max(value = 3, message = "类型超限")
    private Integer type;
    @NotNull(message = "点赞内容ID不能为空")
    private Long hostId;
    @NotNull(message = "点赞操作不能为空")
    private Boolean like;
}
