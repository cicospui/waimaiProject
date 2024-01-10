package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * @Description:
 * @Author: 徐晓宇
 * @Date: 2024/1/10 16:38
 */
public interface DishService {
    //新增菜品和对应口味
    public void saveWithFlavor(DishDTO dishDTO);
}
