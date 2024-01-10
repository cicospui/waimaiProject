package com.sky.aspect;

import com.aliyuncs.ecs.model.v20140526.DescribeVpcsResponse;
import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/*
自定义切面类，实现公共字段自动填充的处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /*
    切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {}
    /*
    前置通知，在通知中进行公共字段的赋值.
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始公共字段自动填充...");

        // 获取被拦截到的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();// 方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);// 获取方法上的注解
        OperationType operationType = autoFill.value();// 获取注解上的操作类型

        // 获取当前被拦截方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            log.info("当前被拦截方法没有参数，不需要进行公共字段自动填充...");
            return;
        }
        Object entity = args[0];//获取employee的实体对象

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();// 当前时间
        Long currentId = BaseContext.getCurrentId();// 当前登录用户的id

        // 根据不同的操作类型进行赋值，可以通过反射的方式进行赋值
        if(operationType == OperationType.INSERT){
            try {
                // 获取实体对象的class对象
                Class<?> entityClass = entity.getClass();

                // 获取实体对象的创建时间属性
                Method setCreateTimeMethod = entityClass.getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                // 获取实体对象的更新时间属性
                Method setUpdateTimeMethod = entityClass.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                // 获取实体对象的创建人属性
                Method setCreateUserMethod = entityClass.getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                // 获取实体对象的更新人属性
                Method setUpdateUserMethod = entityClass.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 赋值
                setCreateTimeMethod.invoke(entity, now);
                setUpdateTimeMethod.invoke(entity, now);
                setCreateUserMethod.invoke(entity, currentId);
                setUpdateUserMethod.invoke(entity, currentId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType == OperationType.UPDATE){
            try {
                // 获取实体对象的class对象
                Class<?> entityClass = entity.getClass();

                // 获取实体对象的更新时间属性
                Method setUpdateTimeMethod = entityClass.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                // 获取实体对象的更新人属性
                Method setUpdateUserMethod = entityClass.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 赋值
                setUpdateTimeMethod.invoke(entity, now);
                setUpdateUserMethod.invoke(entity, currentId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
