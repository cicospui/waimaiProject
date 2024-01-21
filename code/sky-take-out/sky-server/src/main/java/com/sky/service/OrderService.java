package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;

/**
 * @Description: 用户订单接口
 * @Author: 徐晓宇
 * @Date: 2024/1/21 14:53
 */
public interface OrderService {


    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
