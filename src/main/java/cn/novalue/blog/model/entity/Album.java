package cn.novalue.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 相册表(Album)实体类
 *
 * @author makejava
 * @since 2020-09-01 22:51:54
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("album")
public class Album extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = -99884709181225252L;

    private Long userId;
    /**
     * 相册名称
     */
    private String bname;
    /**
     * 权限
     */
    private Integer auth;
    /**
     * 所含相册数量
     */
    private Integer total;


}