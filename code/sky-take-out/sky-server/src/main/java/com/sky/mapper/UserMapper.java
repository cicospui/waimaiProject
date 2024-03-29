package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description: 微信用户的mapper
 * @Author: 徐晓宇
 * @Date: 2024/1/16 19:49
 */
@Mapper
public interface UserMapper {

    //根据openid查询用户
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    //插入用户
    void insert(User user);

    @Select("select * from user where id = #{userId}")
    User getById(Long userId);

    //根据map查询用户数量
    Integer countByMap(Map map);
}
