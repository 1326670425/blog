package cn.novalue.blog.model.entity;


import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("achievement_type")
public class AchievementType implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增主键    
    @TableField("id")
    @TableId
    private Long id;
    // 荣誉类型，比如论文，专利等    
    @TableField("type_name")
    private String typeName;
}