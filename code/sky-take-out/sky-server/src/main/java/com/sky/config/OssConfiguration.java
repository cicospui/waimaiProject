package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: OSS的配置类，用于创建AliOssUtil对象
 * @Author: 徐晓宇
 * @Date: 2024/1/10 15:52
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @ConditionalOnMissingBean//当容器中没有指定Bean的情况下创建该对象,即唯一创建就够用了
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始创建阿里云文件上传对象...", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
