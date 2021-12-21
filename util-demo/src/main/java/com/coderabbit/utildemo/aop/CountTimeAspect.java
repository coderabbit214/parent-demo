package com.coderabbit.utildemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Mr_J
 * @Date: 2021/12/21 5:24 下午
 */
@Component
@Aspect
@Slf4j
public class CountTimeAspect {
    //定义切入点（也可以直接写在下边）
    @Pointcut("@annotation(com.coderabbit.utildemo.annotation.CountTime)")
    public void countTime(){

    }

    @Around("countTime()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
            long beginTime = System.currentTimeMillis();
            object = joinPoint.proceed();
            String serviceName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            log.info("\nController Name:{}\nService Name:{}\n响应耗时{}",className,serviceName,System.currentTimeMillis() - beginTime +"ms");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}
