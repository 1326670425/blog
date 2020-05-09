package cn.novalue.blog.model.entity;

import java.io.Serializable;

import cn.novalue.blog.model.enums.GenderEnum;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 用户名    
    @TableField("username")
    private String username;
    // 密码    
    @TableField("password")
    private String password;
    // 头像地址    
    @TableField("avatar")
    private String avatar;
    // 性别    
    @TableField("gender")
    private GenderEnum gender;
    // 邮箱    
    @TableField("email")
    private String email;
    // 电话    
    @TableField("tel_number")
    private String telNumber;
}