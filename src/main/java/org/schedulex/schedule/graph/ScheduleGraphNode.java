package org.schedulex.schedule.graph;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.schedulex.JobEntry;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class ScheduleGraphNode extends JobEntry {

    private List<ScheduleGraphNode> nextNodes = new LinkedList<ScheduleGraphNode>();

    public void addAfter(ScheduleGraphNode node){
        nextNodes.add(node);
    }

    public ScheduleGraphNode getIndex(int index){
        return nextNodes.get(index);
    }


}
