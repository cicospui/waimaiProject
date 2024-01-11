package com.sky.service;

import com.sky.dto.SetmealDTO;

/**
 * @Description: 套餐相关接口
 * @Author: 徐晓宇
 * @Date: 2024/1/11 19:00
 */
public interface SetmealService {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}