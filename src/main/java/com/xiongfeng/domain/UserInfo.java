package com.xiongfeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/30 0:02
 * @description
 */
@Data
@TableName("user_info")
public class UserInfo {

    private final static Long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String introduce;
    private String city;
    private Long fans;
    private Long follow;
    private Date birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
