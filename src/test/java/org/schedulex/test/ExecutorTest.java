package org.schedulex.test;

import org.junit.Test;
import org.schedulex.invoke.pool.JobRunnerExecutor;

/**
 * Created by Norman S L Dai on 2017/9/8.
 */
public class ExecutorTest {



    @Test
    public void test(){

        JobRunnerExecutor executor = JobRunnerExecutor.getInstance();
        executor.getExecutor().execute(new Runnable() {
            public void run() {
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhh");
            }
        });

    }
}
