package org.schedulex;


import org.schedulex.annotations.JobExecutor;
import org.schedulex.annotations.Join;
import org.schedulex.annotations.ShardStrategy;
import org.schedulex.common.utils.ReflectUtil;
import org.schedulex.common.utils.StringUtil;
import org.schedulex.exceptions.SchedulexBasicException;

import java.lang.reflect.Method;

/**
 * 注解类型作业描述解析器
 */
public class AnnotationJobDescriptionParser implements JobDescriptionParser{


    /**
     * 解析
     * @param object
     * @return
     */
    public JobDescription[] parser(Object object) {

        if (null == object) {
            throw new SchedulexBasicException("Invalid args NULL!");
        }

        Class jobClass = null;

        if (object instanceof Class) {
            jobClass = (Class) object;
        }else {
            jobClass = object.getClass();
        }
        return parseForClass(jobClass);
    }

    /**
     * 通过 类信息解析作业描述信息
     * @param clazz
     * @return
     */
    private JobDescription[] parseForClass(Class clazz){

        /**  获取实现了JobExecutor注解的所有方法*/
        Method[] methods = ReflectUtil.getMethodsByAnnotation(JobExecutor.class, clazz);

        return buildDesc(clazz.getName(),methods);
    }

    /**
     * 构建
     * @param methods
     * @return
     */
    private JobDescription[] buildDesc(String className,Method[] methods){

        JobDescription[] descriptionArray = null;

        String classKey = className;

        if(null != methods && methods.length > 0 ){

            descriptionArray = new JobDescription[methods.length];

            int i = 0;
            for(Method method : methods){

                JobDescription description = new JobDescription();

                JobExecutor jobExecutor = ReflectUtil.getAnnotationInstance(
                        ReflectUtil.getAnnotationFromMethod(JobExecutor.class, method),JobExecutor.class);

                Join join = ReflectUtil.getAnnotationInstance(
                        ReflectUtil.getAnnotationFromMethod(Join.class, method),Join.class);

                ShardStrategy shardStrategy = ReflectUtil.getAnnotationInstance(
                        ReflectUtil.getAnnotationFromMethod(ShardStrategy.class, method),ShardStrategy.class);

                if(null != join){
                    description.setJoinTarget(join.target());
                }

                if(null != shardStrategy){

                    description.setShardStrategyType(shardStrategy.strategy());
                    description.setShardNumber(shardStrategy.number());
                }

                String jobName = StringUtil.isEmpty(jobExecutor.name())?method.getName():jobExecutor.name();
                description.setJobName(jobName);

                description.setExpression(jobExecutor.expression());

                description.setJobKey(classKey+"@"+jobName);

                descriptionArray[i] = description;

                i ++;
            }

        }
        return descriptionArray;
    }


}
