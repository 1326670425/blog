package cn.novalue.blog.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("resume_general")
public class ResumeGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 用户id    
    @TableField("user_id")
    private Long userId;
    // 姓名    
    @TableField("real_name")
    private String realName;
    // 生日    
    @TableField("birthday")
    private LocalDate birthday;
    // 头像，证件照片    
    @TableField("profile_picture")
    private String profilePicture;
    // 现职位    
    @TableField("position")
    private String position;
    // 现居地    
    @TableField("location")
    private String location;
    // 个人简介    
    @TableField("introduction")
    private String introduction;
    // 语言    
    @TableField("language_type")
    private String languageType;
    // 逻辑删除    
    @TableField("deleted")
    private Integer deleted;
}