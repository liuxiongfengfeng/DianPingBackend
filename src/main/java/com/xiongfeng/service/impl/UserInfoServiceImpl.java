package com.xiongfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiongfeng.domain.UserInfo;
import com.xiongfeng.mapper.UserInfoMapper;
import com.xiongfeng.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 刘雄锋
 * @version 1.0
 * @date 2022/11/30 0:22
 * @description
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
