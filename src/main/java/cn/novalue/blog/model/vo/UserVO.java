package cn.novalue.blog.model.vo;

import cn.novalue.blog.model.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Wu Yangjie
 * @date 2020-05-09
 */

@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String telNumber;
    private String avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLogin;
    private GenderEnum gender;
}
