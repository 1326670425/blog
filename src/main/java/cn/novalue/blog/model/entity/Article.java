package cn.novalue.blog.model.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@TableName("article")
public class Article extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 作者id    
    @TableField("user_id")
    private Long userId;
    // 文章标题
    @TableField("title")
    private String title;
    // 文章摘要    
    @TableField("description")
    private String description;
    // 文章内容    
    @TableField("content")
    private String content;
    // 编辑器类型，0：md，1：富文本    
    @TableField("type")
    private Integer type;
    // 文章状态，0：草稿，1：发布    
    @TableField("status")
    private Integer status;
    // 获赞个数    
    @TableField("like_num")
    private Integer likeNum;
}