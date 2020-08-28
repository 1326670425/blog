package cn.novalue.blog.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("resume_education")
public class ResumeEducation implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 所属用户id    
    @TableField("user_id")
    private Long userId;
    // 学校    
    @TableField("school")
    private String school;
    // 身份，本科、硕士、访问学者等    
    @TableField("identity")
    private String identity;
    // 开始时间    
    @TableField("start_time")
    private LocalDate startTime;
    // 结束时间    
    @TableField("end_time")
    private LocalDate endTime;
    // 语言    
    @TableField("language_type")
    private String languageType;
    // 逻辑删除    
    @TableField("deleted")
    private Integer deleted;
}