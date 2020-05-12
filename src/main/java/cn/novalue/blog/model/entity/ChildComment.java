package cn.novalue.blog.model.entity;

import java.time.LocalDateTime;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("child_comment")
public class ChildComment implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    // 评论内容    
    @TableField("content")
    private String content;
    // 所属一级评论id    
    @TableField("parent_id")
    private Long parentId;
    // 发表者id    
    @TableField("user_id")
    private Long userId;
    // 逻辑删除
    @TableField("deleted")
    private Integer deleted;
    // 发表时间    
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    // 获赞数量    
    @TableField("like_num")
    private Integer likeNum;
}