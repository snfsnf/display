package com.snf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TestAssigneeUel {
    /**
     * 测试流程部署
     * 会在ACT_RE_DEPLOYMENT,ACT_RE_PROCDEF表插入数据
     */
    @Test
    public void testDeployment(){
//        1.创建processEngine
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取repositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-uel")
                .addClasspathResource("bpmn/evection-uel.bpmn20.xml")
                .deploy();
//        4.输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());

    }

    /**
     * act_ru_variable 会添加变量
     */
    @Test
    public void startAssigneeUel(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        HashMap<String,Object> assigneeMap = new HashMap<>();
        assigneeMap.put("assignee0","张三");
        assigneeMap.put("assignee1","李经理");
        assigneeMap.put("assignee2","王总经理");
        assigneeMap.put("assignee3","赵会计");
        runtimeService.startProcessInstanceByKey("myEvection1",assigneeMap);

    }

}
