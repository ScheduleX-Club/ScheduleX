package org.schedulex;


/**
 * 作业描述解析器
 * 用于解析解析获得作业描述
 */
public interface JobDescriptionParser {


    /**
     * 解析
     * @param object
     * @return
     */
    JobDescription[] parser(Object object);

}
