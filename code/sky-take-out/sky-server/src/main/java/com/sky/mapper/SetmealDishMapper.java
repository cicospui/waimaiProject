package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜品套餐关联表
 * @Author: 徐晓宇
 * @Date: 2024/1/10 19:43
 */
@Mapper
public interface SetmealDishMapper {

    //根据菜品id查询套餐id

    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量保存套餐和菜品的关联关系
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
