package com.sicdlib.util.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *自定义注解 拦截Controller
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ForService {
    String description() default "";
//    String currentColumn()  default "";
//    String strategyID() default "";
//    String oldValue() default "";
//    String newValue() default "";
}
