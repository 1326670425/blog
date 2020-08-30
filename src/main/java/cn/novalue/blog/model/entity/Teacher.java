package cn.novalue.blog.model.entity;


import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 用户id，关联基础用户表user    
    @TableField("user_id")
    private Long userId;
    // 职称    
    @TableField("title")
    private String title;
    // 所在课题组id    
    @TableField("research_group_id")
    private Long researchGroupId;
}