package org.schedulex.test;

import org.junit.Test;
import org.schedulex.AnnotationJobDescriptionParser;
import org.schedulex.JobDescription;
import org.schedulex.JobDescriptionContainer;
import org.schedulex.JobDescriptionParser;
import org.schedulex.schedule.graph.ScheduleGraph;
import org.schedulex.schedule.graph.ScheduleGraphBuilder;
import org.schedulex.schedule.graph.ScheduleGraphNode;

import java.util.Map;

public class ScheduleGraphBuilderTest {


    @Test
    public void test(){

        JobDescriptionContainer descriptionContainer = JobDescriptionContainer.getInstance();

        JobDescriptionParser parser = new AnnotationJobDescriptionParser();

        JobDescription[] descriptions1 = parser.parser(new TestJob());
        JobDescription[] descriptions2 = parser.parser(new TestJob2());
        descriptionContainer.addJobDescriptions(descriptions1);
        descriptionContainer.addJobDescriptions(descriptions2);

        ScheduleGraphBuilder builder = new ScheduleGraphBuilder();

        Map<String, ScheduleGraph> graphMap = builder.builder();

        for(Map.Entry<String,ScheduleGraph> entry: graphMap.entrySet()){
            processGraph(entry.getValue());
        }




    }


    private void processGraph(ScheduleGraphNode node){

        System.out.println("jobName:"+node.getJobName());

        for(ScheduleGraphNode graphNode : node.getNextNodes()){
            processGraph(graphNode);
        }

    }





}
