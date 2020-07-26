package cn.novalue.blog.model.entity;


import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@TableName("u2u_notify")
public class U2uNotify extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 通知消息类型，比如评论通知，回复通知等    
    @TableField("type")
    private String type;
    // 发送者id，0表示系统通知
    @TableField("sender")
    private Long sender;
    // 发送者用户名
    @TableField("sender_name")
    private String senderName;
    // 接收者id    
    @TableField("receiver")
    private Long receiver;
    // 目标对象id，比如评论通知，是哪个消息的评论    
    @TableField("target_id")
    private Long targetId;
    // 目标对象的描述，比如文章标题
    @TableField("target_desc")
    private String targetDesc;
    // 目标对象类型，比如消息、文章，典型案例：评论可以发在消息、文章等处
    @TableField("target_type")
    private String targetType;
    // 消息内容，比如回复的内容，或者添加好友请求的验证消息    
    @TableField("message")
    private String message;
    // 消息状态，0未读，1已读    
    @TableField("status")
    private Integer status;
}