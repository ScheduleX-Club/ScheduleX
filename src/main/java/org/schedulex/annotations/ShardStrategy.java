package org.schedulex.annotations;

import org.schedulex.annotations.type.ShardStrategyType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 作业分发策略
 */
public @interface ShardStrategy {

    /**
     * 分发模式
     * @return
     */
    ShardStrategyType strategy() default ShardStrategyType.PARALLEL;

    /**
     * 分发数量
     * @return
     */
    int number() default 1;

}
