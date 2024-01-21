package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 徐晓宇
 * @Date: 2024/1/21 14:57
 */
@Mapper
public interface OrderMapper {
    void insert(Orders orders);
}
