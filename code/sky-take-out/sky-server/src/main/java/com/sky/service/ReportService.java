package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import java.time.LocalDate;

/**
 * @Description:
 * @Author: 徐晓宇
 * @Date: 2024/1/27 17:13
 */
public interface ReportService {

    //获取指定时间内的营业额
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    //获取指定时间内的用户数据
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    //获取指定时间内的订单数据
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);
}
