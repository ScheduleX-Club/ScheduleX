package org.schedulex.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface JobExecutor {

    String name() default "";
    /**
     * 表达式
     * @return
     */
    String expression() default "";


}
