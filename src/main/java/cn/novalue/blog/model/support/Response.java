package cn.novalue.blog.model.support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 通用返回类型
 *
 * @author Wu Yangjie
 * @date 2020-03-03
 */
@Data
@Builder
@AllArgsConstructor
public class Response<T> {
    // 状态码
    private Integer code;
    // 通知信息
    private String msg;
    // 返回数据
    private T data;

    public static <T> Response<T> success() {
        return new Response<>(200, "ok", null);
    }
    public static <T> Response<T> success(T data) {
        return success("ok", data);
    }
    public static <T> Response<T> success(String msg) {
        return success(msg, null);
    }
    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(200, msg, data);
    }


    public static <T> Response<T> failure(Integer code, String msg) {
        return failure(code, msg, null);
    }
    public static <T> Response<T> failure(Integer code, String msg, T data) {
        return new Response<>(code, msg, data);
    }
}
