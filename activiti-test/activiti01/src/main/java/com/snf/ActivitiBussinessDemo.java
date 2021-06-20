package com.snf;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

public class ActivitiBussinessDemo {

    /**
     * 添加业务key到Activiti表
     *
     * 业务id写入ACT_RU_EXECUTION表
     */
    @Test
    public void addBussinessKey(){

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection", "1001");

        System.out.println("bussinessKey=" + instance.getBusinessKey());
    }

    /**
     * 全部流程实例的挂起和激活
     *
     * ACT_RE_PROCDEF  ACT_RU_EXECUTION  ACT_RU_TASK  三张表更新SUSPENSION_STATE_字段
     */
    @Test
    public void suspendAllProcessInstance(){

//        1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取RepositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.查询流程定义，获取流程定义的查询对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .processDefinitionVersion(4)
                .singleResult();
//        4.获取当前流程定义的实例是否都是挂起状态
        boolean suspended = processDefinition.isSuspended();
//        5.获取流程定义id
        String processDefinitionId = processDefinition.getId();
//        6.如果是挂起状态，改为激活状态
        if (suspended) {
            repositoryService.activateProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程定义id：" + processDefinitionId + "已激活");
        }else {
            //        7.如果是激活状态，改为挂起状态
            repositoryService.suspendProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程定义id：" + processDefinitionId + "已挂起");
        }

    }

    /**
     * 挂起、激活单个流程实例
     */
    @Test
    public void suspendSingleProcessInstance(){
//        1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.RunTimeService
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//        3.获取流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("15001")
                .singleResult();
//        4.得到当前流程实例暂停状态
        boolean suspended = processInstance.isSuspended();
//        5.获取流程实例id
        String processInstanceId = processInstance.getId();
//        6.如果暂停，激活
        if (suspended) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例id:" + processInstanceId + "已激活");
        }else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例id:" + processInstanceId + "已挂起");
        }
//        7.如果激活，暂停
    }

    /**
     * 测试完成挂起的任务
     */
    @Test
    public void completeTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = defaultProcessEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processInstanceId("15001")
                .taskAssignee("zhangsan")
                .singleResult();

        taskService.complete(task.getId());
    }
}
