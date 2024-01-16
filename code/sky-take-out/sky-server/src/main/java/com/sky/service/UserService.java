package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Description: 用户接口
 * @Author: 徐晓宇
 * @Date: 2024/1/16 19:33
 */
public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);


}
