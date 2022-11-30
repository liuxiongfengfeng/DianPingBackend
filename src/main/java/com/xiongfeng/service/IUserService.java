package com.xiongfeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiongfeng.domain.User;
import com.xiongfeng.dto.LoginFormDTO;
import com.xiongfeng.dto.Result;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 16:44
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginForm
     */
    Result login(LoginFormDTO loginForm);

    Result sendCode(String phone);

}
