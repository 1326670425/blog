package cn.novalue.blog.model.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册参数实体
 *
 * @author Wu Yangjie
 * @date 2020-05-09
 */
@Data
public class RegisterParam {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
