package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * @Description:
 * @Author: 徐晓宇
 * @Date: 2024/1/27 17:13
 */
public interface ReportService {

    //获取指定时间内的营业额
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);


}
