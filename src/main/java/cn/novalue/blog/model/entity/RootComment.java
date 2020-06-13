package cn.novalue.blog.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("root_comment")
public class RootComment implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    // 发表作者id    
    @TableField("user_id")
    private Long userId;
    // 评论内容    
    @TableField("content")
    private String content;
    // 获赞数量    
    @TableField("like_num")
    private Integer likeNum;
    // 逻辑删除    
    @TableField("deleted")
    private Integer deleted;
    // 发表时间    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 评论类型：文章评论或消息评论    
    @TableField("type")
    private Integer type;
    // 所属消息或文章的id    
    @TableField("host_id")
    private Long hostId;
}