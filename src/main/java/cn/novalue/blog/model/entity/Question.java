package cn.novalue.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题表(Question)实体类
 *
 * @author makejava
 * @since 2020-09-01 21:36:14
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("question")
public class Question extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -44373637599224763L;

    private Long userId;

    private String content;

    private String answer;


}