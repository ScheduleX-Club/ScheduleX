package org.schedulex;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobEntry {

    /**
     * jobKey = Class_full_name + jobName;
     * 用于表示一个作业的唯一标识
     */
    private String jobKey;

    /**
     * jobName 定义在 JobExecutor 中的作业名称
     */
    private String jobName;

}
