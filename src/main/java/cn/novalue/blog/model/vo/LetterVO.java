package cn.novalue.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Wu Yangjie
 * @date 2020-06-19
 */
@Data
public class LetterVO {
    private Long id;
    private Long sender;
    private String username;
    private String avatar;
    private String content;
    private LocalDateTime createTime;
}
