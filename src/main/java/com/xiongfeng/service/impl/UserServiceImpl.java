package com.xiongfeng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiongfeng.domain.User;
import com.xiongfeng.dto.LoginFormDTO;
import com.xiongfeng.dto.Result;
import com.xiongfeng.dto.UserDTO;
import com.xiongfeng.mapper.UserMapper;
import com.xiongfeng.service.IUserService;
import com.xiongfeng.utils.RedisConstants;
import com.xiongfeng.utils.RegexUtil;
import com.xiongfeng.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 16:47
 * @description 用户业务层
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     * @param loginForm
     * @return
     */
    @Override
    public Result login(LoginFormDTO loginForm) {
        String phone = loginForm.getPhone();
        if (RegexUtil.isPhoneInvalid(phone)){
            return Result.fail("手机号码格式错误");
        }
        String code = loginForm.getCode();

        String cacheCode = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + phone);

        if (cacheCode == null || !cacheCode.equals(code)){
            return Result.fail("验证码错误");
        }
        User user = query().eq("phone", phone).one();
        if (user == null){
            user = this.createUserWithPhone(phone);
        }

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        String token = UUID.randomUUID().toString(true);
        Map<String, Object> userDtoMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString()));

        String tokenKey = RedisConstants.LOGIN_USER_KEY+token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userDtoMap);
        stringRedisTemplate.expire(tokenKey, Duration.ofSeconds(RedisConstants.LOGIN_USER_TTL));
        return Result.ok(token);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Override
    public Result sendCode(String phone) {
        String key = RedisConstants.LOGIN_CODE_KEY + phone;
        if (stringRedisTemplate.opsForValue().get(key) != null){
            return Result.ok();
        }
        if (RegexUtil.isPhoneInvalid(phone)){
            return Result.fail("手机号码格式错误");
        }
        String code = RandomUtil.randomNumbers(6);

        stringRedisTemplate.opsForValue().set(key,code);
        stringRedisTemplate.expire(key,Duration.ofSeconds(RedisConstants.LOGIN_CODE_TTL));
        log.debug("验证码发送成功："+code);
        return Result.ok();
    }

    /**
     * 根据电话号码创建用户
     * @param phone
     * @return
     */
    private User createUserWithPhone(String phone){
        User user = new User();
        user.setPhone(phone);
        user.setNickName(SystemConstants.USER_NICK_NAME_PREFIX+RandomUtil.randomString(10));
        save(user);
        return user;
    }
}
