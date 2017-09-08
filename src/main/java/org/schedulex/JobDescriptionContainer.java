package org.schedulex;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 作业描述信息容器
 */
@Getter
@Setter
public class JobDescriptionContainer {

    private List<JobDescription> jobDescriptions = new LinkedList<JobDescription>();

    private Map<String,JobDescription> descriptionMap = new HashMap<String, JobDescription>();

    private static JobDescriptionContainer instance = new JobDescriptionContainer();

    private JobDescriptionContainer(){}

    public static JobDescriptionContainer getInstance(){
        if (instance == null) {
            synchronized (JobDescriptionContainer.class) {
                if (instance == null) {
                    instance = new JobDescriptionContainer();
                }
            }
        }
        return instance;
    }

    /**
     * 添加作业描述到列表中
     * @param descriptions
     */
    public void addJobDescriptions(JobDescription[] descriptions){

        if(null != descriptions && descriptions.length > 0){
            for(JobDescription description : descriptions){
                this.jobDescriptions.add(description);
                descriptionMap.put(description.getJobKey(),description);
            }
        }

    }

    /**
     * 通过JOB_KEY 获取作业描述
     * @param jobKey
     * @return
     */
    public JobDescription getDescriptionByKey(String jobKey){

        if(this.descriptionMap.containsKey(jobKey)){
            return this.descriptionMap.get(jobKey);
        }
        return null;
    }


}
