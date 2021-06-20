package com.snf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

public class TestCreat {

    /**
     * 使用activiti提供默认方法创建mysql表
     */
    @Test
    public void testCreateDBTable(){
//        默认方式
//        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        System.out.println(defaultProcessEngine);

//        自定义方式，配置文件名和bean名称可以自定义
        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println(processEngine);
    }
}
