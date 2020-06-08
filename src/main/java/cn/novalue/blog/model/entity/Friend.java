package cn.novalue.blog.model.entity;


import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@TableName("friend")
public class Friend extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    // 好友1ID    
    @TableField("user_id_1")
    private Long userId1;
    // 好友2ID    
    @TableField("user_id_2")
    private Long userId2;
    // 状态字段，区分强、弱、单向好友关系，保留字段。    
    @TableField("relation")
    private Integer relation;
    // 好友1方的分组    
    @TableField("user_group_1")
    private String userGroup1;
    // 好友2方所在的分组    
    @TableField("user_group_2")
    private String userGroup2;
}