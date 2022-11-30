package com.xiongfeng.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.xiongfeng.dto.Result;
import com.xiongfeng.dto.UserDTO;
import com.xiongfeng.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/29 19:39
 * @description
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO userDto = UserHolder.getUserDto();
        if (BeanUtil.isEmpty(userDto)) {
            response.setStatus(401);
            response.getWriter().print(Result.fail("用户未登录"));
            return false;
        }
        return true;
    }
}
