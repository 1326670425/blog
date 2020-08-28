package cn.novalue.blog.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("resume_work")
public class ResumeWork implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 所属用户id    
    @TableField("user_id")
    private Long userId;
    // 公司或企业    
    @TableField("company")
    private String company;
    // 职位    
    @TableField("position")
    private String position;
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