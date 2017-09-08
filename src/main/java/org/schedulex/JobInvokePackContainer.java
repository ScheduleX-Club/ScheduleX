package org.schedulex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 作业执行实例容器
 */
public class JobInvokePackContainer {

    private List<JobInvokePack> jobInvokePacks = new LinkedList<JobInvokePack>();

    private Map<String,JobInvokePack> jobInvokePackMap = new HashMap<String, JobInvokePack>();

    private static JobInvokePackContainer instance = new JobInvokePackContainer();


    private JobInvokePackContainer(){}

    public static JobInvokePackContainer getInstance(){
        if (instance == null) {
            synchronized (JobInvokePackContainer.class) {
                if (instance == null) {
                    instance = new JobInvokePackContainer();
                }
            }
        }
        return instance;
    }

    /**
     * 添加作业列表中
     * @param invokePacks
     */
    public void addJobDescriptions(JobInvokePack[] invokePacks){

        if(null != invokePacks && invokePacks.length > 0){
            for(JobInvokePack invokePack : invokePacks){
                this.jobInvokePacks.add(invokePack);
                jobInvokePackMap.put(invokePack.getJobKey(),invokePack);
            }
        }

    }

    /**
     * 通过JOB_KEY 获取作业
     * @param jobKey
     * @return
     */
    public JobInvokePack getDescriptionByKey(String jobKey){

        if(this.jobInvokePackMap.containsKey(jobKey)){
            return this.jobInvokePackMap.get(jobKey);
        }
        return null;
    }
}
