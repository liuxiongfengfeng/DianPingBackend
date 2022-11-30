package com.xiongfeng.utils;

import com.xiongfeng.dto.UserDTO;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/29 19:41
 * @description
 */
public class UserHolder {
    private final static ThreadLocal<UserDTO> THREAD_LOCAL = new ThreadLocal<>();

    public static void saveUserDTO(UserDTO userDTO){
        THREAD_LOCAL.set(userDTO);
    }

    public static UserDTO getUserDto(){
        return THREAD_LOCAL.get();
    }

    public static void removeUserDTO(){
        THREAD_LOCAL.remove();
    }


}
