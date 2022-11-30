package com.xiongfeng.config;

import com.xiongfeng.interceptor.LoginInterceptor;
import com.xiongfeng.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/29 20:17
 * @description
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).order(0);
        registry.addInterceptor(new LoginInterceptor()).
                excludePathPatterns(
                        "/user/login",
                        "/user/code"
                ).order(1);
    }
}
