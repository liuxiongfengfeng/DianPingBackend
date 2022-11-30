package com.xiongfeng.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.xiongfeng.dto.Result;
import com.xiongfeng.dto.UserDTO;
import com.xiongfeng.utils.RedisConstants;
import com.xiongfeng.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/29 19:28
 * @description
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        if (token == null){
            return true;
        }
        String key = RedisConstants.LOGIN_USER_KEY + token;
        stringRedisTemplate.expire(key, Duration.ofSeconds(RedisConstants.LOGIN_USER_TTL));

        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        UserHolder.saveUserDTO(userDTO);
        return true;
    }
}
