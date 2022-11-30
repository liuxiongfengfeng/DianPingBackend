package com.xiongfeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author 刘雄锋
 * @version 1.0
 */
@Data
@ToString
@TableName("user")
public class User {
    private final static Long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String phone;
    private String password;
    private String nickName;
    private String icon = "";
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
