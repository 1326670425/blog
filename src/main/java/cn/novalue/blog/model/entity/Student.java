package cn.novalue.blog.model.entity;

import java.time.LocalDate;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 关键基础用户表    
    @TableField("user_id")
    private Long userId;
    // 身份，硕博等    
    @TableField("title")
    private String title;
    // 关联导师表id    
    @TableField("tutor")
    private Long tutor;
    // 入学年份    
    @TableField("enrollment_time")
    private LocalDate enrollmentTime;
    // 毕业年份    
    @TableField("graduation_time")
    private LocalDate graduationTime;
    // 所在课题组id    
    @TableField("research_group_id")
    private Long researchGroupId;
}