package org.schedulex.invoke;


import org.schedulex.configuration.Configuration;
import org.schedulex.configuration.DescriptionScanType;
import org.schedulex.configuration.InstanceType;
import org.schedulex.configuration.SystemInvokedType;
import org.schedulex.exceptions.SchedulexBasicException;

/**
 * big Boss
 */
public class ScheduleX {

    private static ScheduleX instance;

    private boolean configFlag = false;

    private ScheduleX(){}

    public static ScheduleX getInstance(){
        if (instance == null) {
            synchronized (ScheduleX.class) {
                if (instance == null) {
                    instance = new ScheduleX();
                }
            }
        }
        return instance;
    }


    public BatchSystem getBatchSystemByClassPath(String path){
        if(!configFlag){
            this.configFlag = true;
            configInitialize();
        }
        return new BatchSystem(path);
    }

    public void configInitialize(){
        this.configFlag = true;
        Configuration configuration = Configuration.getInstance();
        configuration.setDescriptionScanType(DescriptionScanType.ANNOTATION);
        configuration.setInstanceType(InstanceType.CLASS);
        configuration.setSystemInvokedType(SystemInvokedType.STANDARD);
    }

    public void configInitialize(DescriptionScanType scanType,InstanceType instanceType,SystemInvokedType invokedType){
        this.configFlag = true;
        Configuration configuration = Configuration.getInstance();
        configuration.setDescriptionScanType(scanType);
        configuration.setInstanceType(instanceType);
        configuration.setSystemInvokedType(invokedType);
    }


    public void configInitialize(DescriptionScanType scanType){
        this.configFlag = true;
        Configuration configuration = Configuration.getInstance();
        configuration.setDescriptionScanType(scanType);
        configuration.setInstanceType(InstanceType.CLASS);
        configuration.setSystemInvokedType(SystemInvokedType.STANDARD);
    }

}
