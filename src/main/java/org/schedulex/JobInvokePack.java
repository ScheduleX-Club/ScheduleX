package org.schedulex;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Method;


/**
 * 作业执行包扎类 - 保存作业执行的必要信息
 *
 */
@Getter
@Setter
@ToString
public class JobInvokePack extends JobEntry{

    /**
     * 作业执行实例
     */
    private Object jobInstance;

    /**
     * 作业class 信息
     */
    private Class jobClass;

    /**
     * 作业方法
     */
    private Method jobMethod;

    /**
     * 参数列表
     */
    private Class[] argsType;

    /**
     * 返回值类型
     */
    private Object returnType;


}
