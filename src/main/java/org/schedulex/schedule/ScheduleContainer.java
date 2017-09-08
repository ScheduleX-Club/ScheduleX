package org.schedulex.schedule;

import org.schedulex.JobDescriptionContainer;
import org.schedulex.schedule.graph.ScheduleGraph;
import org.schedulex.schedule.graph.ScheduleGraphBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 排班容器
 */
public class ScheduleContainer {

    private Map<String,Schedule> scheduleMap = new HashMap<String, Schedule>();

    private static ScheduleContainer instance = new ScheduleContainer();

    private ScheduleContainer(){}

    public static ScheduleContainer getInstance(){
        if (instance == null) {
            synchronized (ScheduleContainer.class) {
                if (instance == null) {
                    instance = new ScheduleContainer();
                }
            }
        }
        return instance;
    }


    /**
     * 容器初始化
     * 初始化 scheduleMap 并构建 ScheduleGraph
     */
    public void containerInit() {

        ScheduleGraphBuilder scheduleGraphBuilder = new ScheduleGraphBuilder();
        Map<String, ScheduleGraph> scheduleGraphMap = scheduleGraphBuilder.builder();
        JobDescriptionContainer descriptionContainer = JobDescriptionContainer.getInstance();
        if(null != scheduleGraphMap && !scheduleGraphMap.isEmpty()){

            for(Map.Entry<String,ScheduleGraph> entry : scheduleGraphMap.entrySet()){
                String JobKey = entry.getKey();
                ScheduleGraph scheduleGraph = entry.getValue();
                Schedule schedule = new Schedule();
                schedule.setJobKey(JobKey);
                schedule.setJobName(scheduleGraph.getJobName());
                schedule.setScheduleGraph(scheduleGraph);
                schedule.setDescription(descriptionContainer.getDescriptionByKey(JobKey));
                scheduleMap.put(JobKey,schedule);
            }
        }

    }

}
