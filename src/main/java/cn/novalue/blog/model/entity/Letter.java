package cn.novalue.blog.model.entity;

import java.time.LocalDateTime;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("letter")
public class Letter implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
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
    @TableField("create_time")
    private LocalDateTime createTime;
    // 逻辑删除    
    @TableField("deleted")
    private Object deleted;
}