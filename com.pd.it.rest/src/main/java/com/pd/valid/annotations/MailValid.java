package com.pd.valid.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * 用来注解用来导出excel时的枚举类
 * 
 * @author thinkpad
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MailValidImpl.class)
@Documented
public @interface MailValid {
    String message() default "输入的邮箱格式不正确";

    Class<?>[] groups() default {};
}
