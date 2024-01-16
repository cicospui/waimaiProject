package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: User接口的实现类
 * @Author: 徐晓宇
 * @Date: 2024/1/16 19:41
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //微信登录接口
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    public User wxLogin(UserLoginDTO userLoginDTO) {

        String openid = getOpenid(userLoginDTO.getCode());
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断是否是外卖系统的新用户，若无，存下用户id
        User user = userMapper.getByOpenid(openid);

        //如果是新用户，自动完成注册
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        // 返回用户对象
        return user;
    }

    //获取openid
    private String getOpenid(String code){
        //调用微信接口服务，获取当前用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        //判断openid是否为空，如果为空，抛出异常
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
