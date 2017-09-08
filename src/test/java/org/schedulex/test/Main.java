package org.schedulex.test;

import org.schedulex.invoke.BatchSystem;
import org.schedulex.invoke.ScheduleX;

/**
 * Created by Norman S L Dai on 2017/9/8.
 */
public class Main {


    public static void main(String[] args) {

        // 1.get ScheduleX support
        ScheduleX scheduleX = ScheduleX.getInstance();

        // 2.get BatchSystem from ScheduleX with Class path
        BatchSystem batchSystem = scheduleX.getBatchSystemByClassPath("org.schedulex.test");
        //3. start BatchSystem
        batchSystem.startSystem();


    }

}
