package org.schedulex.annotations.type;


/**
 * 作业分发策略
 */
public enum ShardStrategyType {

    /**
     * 分布式
     */
    DISTRIBUTED,

    /**
     * 并发
     */
    PARALLEL;

}
