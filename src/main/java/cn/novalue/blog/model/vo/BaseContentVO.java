package cn.novalue.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 基础VO，抽取Article、Message、Comment的公共字段
 *
 * @author Wu Yangjie
 * @date 2020-06-09
 */
@Data
public class BaseContentVO {
    private Long id;
    private Long userId;
    private String username;
    private String avatar;
    private String content;
    private Integer likeNum;
    // 用户是否已点赞该内容，true表示已赞，false表示未赞
    private Boolean liked;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
