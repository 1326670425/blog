package cn.novalue.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 照片管理(Picture)实体类
 *
 * @author makejava
 * @since 2020-09-02 08:49:16
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("picture")
public class Picture extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 612627962981791354L;

    private Long albumId;
    /**
     * 图片地址
     */
    private String url;
    private  int likeNum;
    private int viewNum;


}