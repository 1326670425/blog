package cn.novalue.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@Data
public class MessageVO {
    private Long id;
    private Long userId;
    private String username;
    private String avatar;
    private String content;
    private Integer commentNum;
    private Integer likeNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
