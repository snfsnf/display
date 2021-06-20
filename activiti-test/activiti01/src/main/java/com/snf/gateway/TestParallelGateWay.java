package com.snf.gateway;

import com.snf.pojo.Evection;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 测试并行网关
 */

public class TestParallelGateWay {

    @Test
    public void testDeployment(){
        //        1.创建processEngine
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取repositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-并行网关")
                .addClasspathResource("bpmn/evection-parallel.bpmn")
                .deploy();
//        4.输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    @Test
    public void testStartProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        String key = "evection-parallel";

        HashMap<String, Object> map = new HashMap<>();

        Evection evection = new Evection();

        evection.setNum(4d);

        map.put("evection",evection);
        runtimeService.startProcessInstanceByKey(key,map);
    }

    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-parallel")
                .taskAssignee("赵六")
                .singleResult();

        if (task != null) {
            String taskId = task.getId();
            System.out.println("taskId="+taskId);
            taskService.complete(taskId);
        }
    }
}
