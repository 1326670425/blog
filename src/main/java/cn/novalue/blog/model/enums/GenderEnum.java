package cn.novalue.blog.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户性别枚举，用于页面展示和数据库存储之间的映射
 *
 * @author Wu Yangjie
 * @date 2020-03-05
 */
public enum GenderEnum implements IEnum<Integer> {
    FEMALE(0, "保密"),
    MALE(1, "男"),
    SECRECY(2, "女");

    private int value;
    @JsonValue
    private String desc;

    GenderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据 desc 构建枚举
     * @param desc 枚举描述
     * @return GenderEnum类型，默认返回 GenderEnum.SECRECY
     */
    public static GenderEnum of(String desc) {
        GenderEnum[] genderEnums = values();
        for (GenderEnum gender : genderEnums) {
            if (gender.getDesc().equals(desc))
                return gender;
        }
        return SECRECY;
    }
    /**
     * 根据 value 构建枚举
     * @param value 枚举值
     * @return GenderEnum类型，默认返回 GenderEnum.SECRECY
     */
    public static GenderEnum of(Integer value) {
        GenderEnum[] genderEnums = values();
        for (GenderEnum gender : genderEnums) {
            if (gender.getValue().equals(value))
                return gender;
        }
        return SECRECY;
    }
    public String getDesc() {
        return this.desc;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }
    @Override
    public String toString() {
        return this.desc;
    }
}
