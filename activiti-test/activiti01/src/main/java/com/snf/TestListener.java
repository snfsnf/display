package com.snf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

public class TestListener {
    @Test
    public void testDeployment(){
//        1.创建processEngine
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取repositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("测试监听器")
                .addClasspathResource("bpmn/demo-listener.bpmn")
                .deploy();
//        4.输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());

    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess(){
//        1.创建processEngines
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取RunTimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//        3.根据流程定义id启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("test-listener");
//        4.输出内容
        System.out.println("流程定义id=" + instance.getProcessDefinitionId());
        System.out.println("流程实例id=" + instance.getId());
        System.out.println("当前活动id=" + instance.getActivityId());

    }
}
