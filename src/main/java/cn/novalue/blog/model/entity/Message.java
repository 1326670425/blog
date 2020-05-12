package cn.novalue.blog.model.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@TableName("message")
public class Message extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 作者id    
    @TableField("user_id")
    private Long userId;
    // 消息内容    
    @TableField("content")
    private String content;
    // 获赞个数    
    @TableField("like_num")
    private Integer likeNum;
}