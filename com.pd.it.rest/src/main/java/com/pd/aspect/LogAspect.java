package com.pd.aspect;

import com.pd.common.calobject.TimerCO;
import com.pd.it.common.businessobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.pd.it.common.util.StaticTool.*;

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
			jp.getArgs();
			String paramStr = toStr(jp.getArgs());
			log.info(formatStr("%s.%s in:%s", classPath, methodName, paramStr));
			TimerCO timer = new TimerCO(formatStr("%s.%s", classPath, methodName));
			try {
				Object result = jp.proceed();
				timer.end();
				String resultStr = toStr(result);
				Long endTime = System.currentTimeMillis();
				if (result instanceof ResultVO) {
					ResultVO rsVO = (ResultVO) result;
					rsVO.setStartTime(timer.getStart());
					rsVO.setEndTime(endTime);
					rsVO.setUseTime(timer.getUsedTime());
				}
				log.info(formatStr("%s.%s out:%s,use time: %d", classPath, methodName, resultStr, timer.getUsedTime()));
				return result;
			}catch(Exception e){
				error(timer.getName(),e);
			}
		}
		return jp.proceed();
	}
}