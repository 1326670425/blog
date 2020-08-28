package cn.novalue.blog.model.entity;

import java.time.LocalDate;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("resume_achievement")
public class ResumeAchievement implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 标题，描述，比如论文题目，奖项名称    
    @TableField("ach_title")
    private String achTitle;
    // 关联用户    
    @TableField("user_id")
    private Long userId;
    // 荣誉类型，关联achievement_type表    
    @TableField("type_id")
    private Long typeId;
    // 获得时间    
    @TableField("ach_time")
    private LocalDate achTime;
    // 相关描述    
    @TableField("ach_description")
    private String achDescription;
    // 相关组织机构    
    @TableField("ach_organization")
    private String achOrganization;
    // 逻辑删除    
    @TableField("deleted")
    private Integer deleted;
    // 语言    
    @TableField("language_type")
    private String languageType;
}