package cn.novalue.blog.model.support;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 验证码实体
 *
 * @author Wu Yangjie
 * @date 2020-06-10
 */
@Data
public class CheckCode {
    // 有效时间，单位：秒
    private static final long EXPIRATION = 60;
    // 验证码
    private String code;
    // 过期时间
    private LocalDateTime expireTime;

    private CheckCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 默认6位验证码
     *
     * @return 验证码实体
     */
    public static CheckCode create() {
        return create(6);
    }

    /**
     * 创建验证码实体
     *
     * @param length 验证码长度
     * @return 验证码实体
     */
    public static CheckCode create(Integer length) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        // 0-9之间的数字
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return new CheckCode(sb.toString(), LocalDateTime.now().plusSeconds(EXPIRATION));
    }

    /**
     * 判断验证码是否过期
     *
     * @param code 验证码
     * @return true为过期，false未过期
     */
    public static boolean isExpired(CheckCode code) {
        return code.getExpireTime().isBefore(LocalDateTime.now());
    }
}
