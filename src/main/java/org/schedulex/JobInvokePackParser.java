package org.schedulex;


public interface JobInvokePackParser {


    /**
     * 作业执行实例解析器
     * @param object
     * @return
     */
    JobInvokePack[] parser(Object object);


}
