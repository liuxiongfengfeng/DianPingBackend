package com.xiongfeng.controller;

import com.xiongfeng.domain.UserInfo;
import com.xiongfeng.dto.LoginFormDTO;
import com.xiongfeng.dto.Result;
import com.xiongfeng.service.IUserInfoService;
import com.xiongfeng.service.IUserService;
import com.xiongfeng.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/28 19:23
 * @description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping("/code")
    public Result sendCode(HttpServletRequest request, @RequestParam String phone){
        return userService.sendCode(phone);
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request,@RequestBody LoginFormDTO loginForm){
        return userService.login(loginForm);
    }

    @GetMapping("/me")
    public Result me(){
        return Result.ok(UserHolder.getUserDto());
    }

    @GetMapping("/info/{id}")
    public Result userInfo(@PathVariable("id") int id){
        UserInfo userInfo = userInfoService.getById(id);
        if (userInfo == null){
            userInfo = new UserInfo();
            userInfo.setFollow(0L);
            userInfo.setFans(0L);
        }
        return Result.ok(userInfo);
    }

}
