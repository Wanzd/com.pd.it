package com.pd.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来注解用来导出excel时的枚举类
 * 
 * @author thinkpad
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Export {

	String titleName();

	String sheetName();

	int batchSize() default 1000;

}
