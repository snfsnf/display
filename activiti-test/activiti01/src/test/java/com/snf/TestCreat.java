package com.snf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

public class TestCreat {

    /**
     * 使用activiti提供默认方法创建mysql表
     */
    @Test
    public void testCreateDBTable(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);
    }
}
