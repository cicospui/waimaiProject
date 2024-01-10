package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 口味表
 * @Author: 徐晓宇
 * @Date: 2024/1/10 16:56
 */
@Mapper
public interface DishFlavorMapper {
    //批量插入口味数据
    void insertBatch(List<DishFlavor> flavors);

    //根据菜品删除对应口味数据
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long dishId);

    void deleteByDishIds(List<Long> dishIds);
}
