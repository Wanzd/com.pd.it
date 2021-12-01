package com.pd.springboot;

import javax.inject.Named;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pd.aspect.TestAspect;

/**
 * @Auther: hugeo.wang
 * @Date: 2018/7/11 11:07
 * @Description:
 */
@Named
@Configuration
@ComponentScan(basePackageClasses = { TestAspect.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {}