package org.schedulex;


import org.schedulex.annotations.JobExecutor;
import org.schedulex.common.utils.ReflectUtil;
import org.schedulex.common.utils.StringUtil;
import org.schedulex.exceptions.SchedulexBasicException;
import org.schedulex.exceptions.SchedulexJobInstanceException;

import java.lang.reflect.Method;

public class StandardJobInvokePackParser implements JobInvokePackParser {


    /**
     * 作业执行实例解析器
     *
     * @param object
     * @return
     */
    public JobInvokePack[] parser(Object object) {

        if (null == object) {
            throw new SchedulexBasicException("Invalid args NULL!");
        }
        Class invokeClass;
        Object instance;
        if (object instanceof Class) {

            invokeClass = (Class) object;
            try {
                instance = invokeClass.newInstance();
            } catch (Exception e) {
                throw new SchedulexJobInstanceException(e);
            }
        } else {
            invokeClass = object.getClass();
            instance = object;
        }
        Method[] methods = ReflectUtil.getMethodsByAnnotation(JobExecutor.class, invokeClass);
        JobInvokePack[] packs = null;

        if (null != methods && methods.length > 0) {
            packs = new JobInvokePack[methods.length];
            int i = 0;
            for (Method method : methods) {

                JobInvokePack pack = new JobInvokePack();
                JobExecutor jobExecutor = ReflectUtil.getAnnotationInstance(
                        ReflectUtil.getAnnotationFromMethod(JobExecutor.class, method), JobExecutor.class);

                String name = StringUtil.isEmpty(jobExecutor.name()) ? method.getName() : jobExecutor.name();

                pack.setJobClass(invokeClass);
                pack.setJobInstance(instance);
                pack.setJobName(name);
                pack.setJobKey(invokeClass.getName() + "@" + name);
                pack.setJobMethod(method);
                pack.setArgsType(method.getParameterTypes());
                pack.setReturnType(method.getReturnType());
                packs[i] = pack;
                i++;
            }

        }
        return packs;
    }


}
