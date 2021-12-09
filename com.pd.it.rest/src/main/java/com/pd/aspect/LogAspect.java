package com.pd.aspect;

import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.toStr;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: hugeo.wang
 * @Date: 2018/7/11 11:07
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Around("@annotation(com.pd.it.common.annotations.Log)")
    public Object beforeLog(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            String classPath = method.getDeclaringClass().getTypeName();
            String methodName = method.getName();
            String paramStr = toStr(jp.getArgs());
            log.info(formatStr("%s.%s in:%s", classPath, methodName, paramStr));
            Object result = jp.proceed();
            String resultStr = toStr(result);
            log.info(formatStr("%s.%s out:%s", classPath, methodName, resultStr));
            return result;
        }
        return jp.proceed();
    }
}