package org.schedulex.invoke.pool;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


public class JobRunnerExecutor {

    private static JobRunnerExecutor instance;

    private JobRunnerExecutor(){}

    private static final int THREAD_NUM = 64;

    private static final int THREAD_QNM = 1024;

    private static final int processorNum = Runtime.getRuntime().availableProcessors();

    private  Executor executor = HydraThreadPool.getExecutor(THREAD_NUM * processorNum,THREAD_QNM * processorNum);

    private  ListeningExecutorService threadPoolExecutor = MoreExecutors.listeningDecorator((ThreadPoolExecutor) executor);


    public static JobRunnerExecutor getInstance(){
        if (instance == null) {
            synchronized (JobRunnerExecutor.class) {
                if (instance == null) {
                    instance = new JobRunnerExecutor();
                }
            }
        }
        return instance;
    }

    public Executor getExecutor(){
        return this.executor;
    }

    public ListeningExecutorService getThreadPoolExecutor(){
        return threadPoolExecutor;
    }





}
