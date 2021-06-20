package com.snf;

import com.snf.pojo.Evection;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 全局变量测试
 */
public class TestVariable {

    /**
     * 测试部署
     */
    @Test
    public void testDeployment(){
        //        1.创建processEngine
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取repositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-gloableVariable")
                .addClasspathResource("bpmn/evection-global2.bpmn")
                .deploy();
//        4.输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    @Test
    public void testStartProcess(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        String key = "evection-global2";

        HashMap<String, Object> map = new HashMap<>();

        Evection evection = new Evection();

        evection.setNum(4d);

        map.put("evection",evection);
        map.put("assignee0","李四");
        map.put("assignee1","王经理");
        map.put("assignee2","赵总经理");
        map.put("assignee3","张会计");

        runtimeService.startProcessInstanceByKey(key,map);
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-global2")
                .taskAssignee("王经理")
                .singleResult();

        if (task != null) {
            taskService.complete(task.getId());
        }
    }
}
