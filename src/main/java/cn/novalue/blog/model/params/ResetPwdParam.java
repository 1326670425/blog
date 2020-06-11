package cn.novalue.blog.model.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Wu Yangjie
 * @date 2020-06-10
 */
@Data
public class ResetPwdParam {
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码6-20个字符")
    private String password;
}
