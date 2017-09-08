package org.schedulex.invoke;


import org.schedulex.*;
import org.schedulex.common.utils.PackageScanner;
import org.schedulex.common.utils.StringUtil;
import org.schedulex.configuration.Configuration;
import org.schedulex.configuration.DescriptionScanType;
import org.schedulex.configuration.InstanceType;
import org.schedulex.configuration.SystemInvokedType;
import org.schedulex.exceptions.SchedulexBasicException;
import org.schedulex.schedule.ScheduleContainer;

import java.util.List;

public class ClassPathDescriptionLoader implements DescriptionLoader<String>{


    /**
     * 加载作业配置信息组装对象
     * @param path
     */
    public void load(String path) {

        if(StringUtil.isNotEmpty(path)){

            Configuration configuration = Configuration.getInstance();

            if(configuration.getDescriptionScanType() == DescriptionScanType.ANNOTATION &&
                    configuration.getInstanceType() == InstanceType.CLASS &&
                    configuration.getSystemInvokedType() == SystemInvokedType.STANDARD){

                List<Class> classes = PackageScanner.getClasses(path);

                JobDescriptionParser parser = new AnnotationJobDescriptionParser();

                JobDescriptionContainer descriptionContainer = JobDescriptionContainer.getInstance();

                JobInvokePackContainer packContainer = JobInvokePackContainer.getInstance();

                JobInvokePackParser packParser = new StandardJobInvokePackParser();

                for(Class clazz : classes){

                    descriptionContainer.addJobDescriptions(parser.parser(clazz));

                    packContainer.addJobDescriptions(packParser.parser(clazz));
                }

                ScheduleContainer scheduleContainer = ScheduleContainer.getInstance();

                scheduleContainer.containerInit();
            }

        }else {
            throw new SchedulexBasicException("Path is empty or not found!");
        }

    }
}
