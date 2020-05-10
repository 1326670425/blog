package cn.novalue.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色实体
 *
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@Data
public class Role {
    // 自增主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 角色名
    @TableField("name")
    private String name;
}
