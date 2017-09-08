package org.schedulex.schedule.graph;

import org.schedulex.JobDescription;
import org.schedulex.JobDescriptionContainer;
import org.schedulex.common.utils.StringUtil;

import java.util.*;

/**
 * ScheduleGraph 构建器
 */
public class ScheduleGraphBuilder {


    /**
     * 构建器
     * 通过从 JobDescriptionContainer 获取所有的作业描述，按照Join将其构建成作业图
     * @return
     */
    public  Map<String,ScheduleGraph> builder(){

        /**  获取左右的作业描述*/
        JobDescriptionContainer descriptionContainer = JobDescriptionContainer.getInstance();

        List<JobDescription> jobDescriptions = descriptionContainer.getJobDescriptions();

        Map<String,ScheduleGraph> graphMap = new HashMap<String, ScheduleGraph>();

        if(null != jobDescriptions && !jobDescriptions.isEmpty()){

            /** 解析主作业*/
            List<JobDescription> primaryJobDesc = getPrimaryJobDesc(jobDescriptions);
            /**  构建作业关系*/

            for(JobDescription primaryDesc : primaryJobDesc){

                ScheduleGraph graph = new ScheduleGraph();
                graph.setJobKey(primaryDesc.getJobKey());
                graph.setJobName(primaryDesc.getJobName());
                graphNodesRecursion(jobDescriptions,graph);
                /**  设置作业图*/
                graphMap.put(primaryDesc.getJobKey(),graph);
            }

        }

        return graphMap;
    }



    /**
     * 作业图递归
     * @param jobDescriptions
     * @param graphNode
     */
    private void graphNodesRecursion(List<JobDescription> jobDescriptions,ScheduleGraphNode graphNode){

        String jobName = graphNode.getJobName();


        for(JobDescription description : jobDescriptions){
            if(StringUtil.isNotEmpty(description.getJoinTarget()) && description.getJoinTarget().equals(jobName)){
                ScheduleGraphNode node = new ScheduleGraphNode();
                node.setJobKey(description.getJobKey());
                node.setJobName(description.getJobName());
                graphNode.addAfter(node);
               /**  递归处理*/
               graphNodesRecursion(jobDescriptions,node);
            }

        }


    }



    /**
     * 解析获取主作业描述
     * @param jobDescriptions
     * @return
     */
    private List<JobDescription> getPrimaryJobDesc(List<JobDescription> jobDescriptions){

        List<JobDescription> primaryJobDesc = new ArrayList<JobDescription>();

        for(JobDescription desc : jobDescriptions){

            if(StringUtil.isNotEmpty(desc.getExpression())){
                primaryJobDesc.add(desc);
            }
        }
        return primaryJobDesc;
    }

}
