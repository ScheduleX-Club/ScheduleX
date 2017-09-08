package org.schedulex;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.schedulex.annotations.type.ShardStrategyType;

/**
 * 作业描述信息
 * 用于保存作业的定义信息
 */

@Setter
@Getter
@ToString
public class JobDescription extends JobEntry{


    /**
     * 作业表达式 expression
     */
    private String expression;

    /**
     * 作业分发/分片策略
     * DISTRIBUTED  分布式模式
     * PARALLEL     本地并发模式
     */
    private ShardStrategyType shardStrategyType;

    /**
     * 分片数 - 该作业被分发的数量
     */
    private int shardNumber;

    /**
     * 作业树 - 该作业是否会加入到某个作业的后面执行
     */
    private String joinTarget;


}
