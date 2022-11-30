package com.xiongfeng.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 18:47
 * @description 用户实体类
 */
@Data
@ToString
public class UserDTO {
    private Long id;
    private String nickName;
    private String icon;
}
