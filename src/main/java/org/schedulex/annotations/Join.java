package org.schedulex.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Join {

    /**
     * 目标作业
     * @return
     */
    String target() default "";

}
