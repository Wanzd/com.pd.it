package com.pd.aspect;

import java.lang.reflect.Method;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pd.it.common.annotations.ICheck;

/**
 * @Auther: hugeo.wang
 * @Date: 2018/7/11 11:07
 * @Description:
 */
@Aspect
@Component
public class TestAspect {

    @Around("execution(* com.pd..CheckService.kkk*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            ICheck icheckAnnotation = method.getAnnotation(ICheck.class);
            if (icheckAnnotation != null) {

                System.out.println("aspect");
                return "aspect";
            }
        }
        System.out.println("around");
        return jp.proceed();
    }

    @Around("execution(* com.pd..CheckService.valid1*(..))")
    public Object aroundCheck(ProceedingJoinPoint jp) throws Throwable {
        return "aspect";
    }

    @Around("@annotation(com.pd.it.common.annotations.ICheck)")
    public Object aroundAnnotationCheck(ProceedingJoinPoint jp) throws Throwable {
        return "aspect";
    }
}