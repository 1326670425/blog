package cn.novalue.blog.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@Data
public class CommentVO {
    private Long id;
    private Long userId;
    private String username;
    private String avatar;
    private String content;
    private Integer likeNum;
    private LocalDateTime createTime;
    private Integer childrenNum;
}
