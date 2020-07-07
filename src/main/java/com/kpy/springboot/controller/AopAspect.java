package com.kpy.springboot.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.controller
 * @data: 2019-8-31 21:21
 * @discription:
 **/
public class AopAspect {
    private static Logger logger = LoggerFactory.getLogger(AopAspect.class);

    @Around("execution(Map com.kpy.springboot.controller.MainController.*(..))")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> map = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            logger.debug("args:{}", args);
            Object val = proceedingJoinPoint.proceed(args);
            map = (Map<String, Object>) val;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            map = new HashMap<>();
            logger.debug("throwable.msg:{}", throwable.toString());
            map.put("msg", throwable.toString());
        }
        map.put("aop", "拦截了一个请求");
        return map;
    }
}
