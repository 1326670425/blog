package cn.novalue.blog.model.enums;

/**
 * 评论类型枚举
 *
 * @author Wu yangjie
 * @date 2020-05-10
 */
public enum CommentType {

    MESSAGE(0, "消息"),
    ARTICLE(1, "文章");

    private int value;
    private String name;

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    CommentType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CommentType valueOf(int i) {
        if (i > values().length - 1)
            throw new RuntimeException("不存在对应类型");
        return values()[i];
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
