package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 * @Author: 徐晓宇
 * @Date: 2024/1/20 17:00
 */
@Mapper
public interface ShoppingCartMapper {

    //动态条件查询
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    //根据id修改商品数量
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    //插入购物车数据
    @Insert("insert into shopping_cart(user_id,dish_id,setmeal_id,name,dish_flavor,image,amount,number,create_time) " +
            "values(#{userId},#{dishId},#{setmealId},#{name},#{dishFlavor},#{image},#{amount},#{number},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);
}
