package cn.novalue.blog.model.entity;


import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("research_group")
public class ResearchGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增id    
    @TableField("id")
    @TableId
    private Long id;
    // 名称    
    @TableField("name")
    private String name;
    // 描述    
    @TableField("description")
    private String description;
    // 组长，关联导师表id    
    @TableField("leader")
    private Long leader;
}