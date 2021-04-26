package com.example.shiro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogCount {

    private final String ExpGetResultDataPonit = "execution(* com.example.shiro.controller.*.*(..))";
    private static final Logger countLogger = LoggerFactory.getLogger("count");

    /**
     * 切入点
     * 匹配com.example.servicecuntian.controller包及其子包下的所有类的所有方法
     */
    @Pointcut(ExpGetResultDataPonit)
    public void pointCut() {

    }

    /**
     * 前置通知，目标方法调用前被调用
     * 打印调用信息
     */
    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] obj = joinPoint.getArgs();
        String str = "";
        for (Object s : obj) {
            str = str + s.toString() + ",";
        }
        // 打印count日志
//        String methodName = joinPoint.getSignature().getName();
        //类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //方法名
        String methodName = methodSignature.getName();
        //参数名数组
        String[] parameters = methodSignature.getParameterNames();
        //参数值
        Object[] args = joinPoint.getArgs();


        countLogger.info(methodName + ";" + str);
    }


}
