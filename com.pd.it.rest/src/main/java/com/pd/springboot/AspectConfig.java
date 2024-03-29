package com.pd.springboot;

import javax.inject.Named;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pd.aspect.LogAspect;
import com.pd.aspect.TestAspect;
import com.pd.aspect.TimeoutAspect;

/**
 * @Auther: hugeo.wang
 * @Date: 2018/7/11 11:07
 * @Description:
 */
@Named
@Configuration
@ComponentScan(basePackageClasses = { TestAspect.class, LogAspect.class, TimeoutAspect.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {}