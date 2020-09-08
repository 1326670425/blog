package cn.novalue.blog.model.entity;

import java.time.LocalDateTime;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("letter")
public class Letter implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    // 发送者id    
    @TableField("sender")
    private Long sender;
    // 接收者id    
    @TableField("receiver")
    private Long receiver;
    // 留言内容    
    @TableField("content")
    private String content;
    // 创建时间    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 逻辑删除
    @TableField("deleted")
    private Integer deleted;
}