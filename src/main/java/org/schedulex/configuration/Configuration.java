package org.schedulex.configuration;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {

    /**
     * 描述扫描类型
     */
    private DescriptionScanType descriptionScanType;

    /**
     * 装载实例类型
     */
    private InstanceType instanceType;

    /**
     * 执行环境
     */
    private SystemInvokedType systemInvokedType;


    private static Configuration instance;

    private Configuration(){}

    public static Configuration getInstance(){
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }


}
