package cn.novalue.blog.model.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
@TableName("notify_friend_Request")
public class NotifyFriendRequest extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 发起者，对应user表id    
    @TableField("sender")
    private Long sender;
    // 接收者，对应user表id    
    @TableField("receiver")
    private Long receiver;
    // 附加验证消息    
    @TableField("message")
    private String message;
    // 状态，0未读，1同意，2拒绝    
    @TableField("status")
    private Integer status;
}