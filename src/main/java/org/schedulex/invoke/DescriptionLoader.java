package org.schedulex.invoke;


/**
 * 作业描述加载
 * @param <T> 加载类型
 */
public interface DescriptionLoader<T> {

    void load(T t);

}
