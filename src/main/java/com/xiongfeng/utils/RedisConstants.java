package com.xiongfeng.utils;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 18:34
 */
public class RedisConstants {
    public final static String LOGIN_CODE_KEY = "login:code:";
    public final static String LOGIN_USER_KEY = "login:token:";
    /**
     * token过期时间
     */
    public final static Long LOGIN_USER_TTL = 1800L;

    /**
     * 验证码过期时间
     */
    public final static Long LOGIN_CODE_TTL = 60L;

}
