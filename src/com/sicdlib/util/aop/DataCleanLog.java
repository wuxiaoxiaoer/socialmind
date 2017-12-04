package com.sicdlib.util.aop;

import java.lang.annotation.*;

/**
 *自定义注解 拦截Controller
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface DataCleanLog {
    String currentTable() default "";
//    String currentColumn()  default "";
//    String strategyID() default "";
//    String oldValue() default "";
//    String newValue() default "";

}
