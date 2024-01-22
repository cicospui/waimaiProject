package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 自定义的定时任务类
 * @Author: 徐晓宇
 * @Date: 2024/1/22 15:31
 */
@Component
@Slf4j
public class MyTask {

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void execute() {
//        log.info("执行自定义定时任务:{}", new Date());
//    }
}
