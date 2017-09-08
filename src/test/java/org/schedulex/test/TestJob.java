package org.schedulex.test;

import org.schedulex.ShardingParameters;
import org.schedulex.annotations.JobExecutor;
import org.schedulex.annotations.Join;
import org.schedulex.annotations.ShardStrategy;
import org.schedulex.annotations.type.ShardStrategyType;

public class TestJob {

    @JobExecutor(name = "TestJob.jobA",expression = "0 0 10,14,16 * * ?")
    @ShardStrategy(strategy = ShardStrategyType.PARALLEL,number = 3)
    public String jobA(ShardingParameters parameters){
        return "jobA invoke";
    }

    @Join(target = "TestJob.jobA")
    @JobExecutor(name = "TestJob.jobB")
    public int jobB(String value){
        System.out.println("jobB:"+value);
        return 2;
    }


    @Join(target = "TestJob.jobB")
    @JobExecutor(name = "TestJob.jobC")
    public void jobC(int value){
        System.out.println("jobB:"+value);
    }


}
