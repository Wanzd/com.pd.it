package com.pd.it.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块注解
 * 
 * @author thinkpad
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
	String name();

	Class voClass();

	Class foClass();

	Class daoClass();

	Class serviceClass();
}
