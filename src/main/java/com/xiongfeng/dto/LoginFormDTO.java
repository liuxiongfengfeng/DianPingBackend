package com.xiongfeng.dto;

import lombok.Data;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 17:12
 */
@Data
public class LoginFormDTO {
    private String phone;
    private String code;
    private String password;
}
