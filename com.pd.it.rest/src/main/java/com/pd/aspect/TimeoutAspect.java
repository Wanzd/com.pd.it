package com.pd.aspect;

import com.pd.it.common.annotations.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;

import static com.pd.it.common.util.StaticTool.formatStr;

/**
 * @Auther: hugeo.wang
 * @Date: 2018/7/11 11:07
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class TimeoutAspect {
    private static final int SLEEP_MINISECONDS = 10;

    @Around("@annotation(com.pd.it.common.annotations.Timeout)")
    public Object aroundTimeout(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Timeout timeoutAnnotation = method.getAnnotation(Timeout.class);
            int timeoutValue = timeoutAnnotation.value();
            Callable<Object> callable = new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try {
                        return jp.proceed();
                    } catch (Throwable e) {
                        return e;
                    }
                }
            };
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Object> future = executorService.submit(callable);
            executorService.shutdown();
            long startTime = System.currentTimeMillis();
            while (!future.isDone()) {
                if (System.currentTimeMillis() > startTime + timeoutValue) {
                    throw new TimeoutException(formatStr("Used time is over %d",timeoutValue));
                }
                TimeUnit.MILLISECONDS.sleep(SLEEP_MINISECONDS);
            }

            return jp.proceed();
        }
        return jp.proceed();
    }
}