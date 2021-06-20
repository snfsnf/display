package com.snf;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Testcandidate {

    @Test
    public void testDeployment(){
        //        1.创建processEngine
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取repositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        3.使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-candidate")
                .addClasspathResource("bpmn/evection-candidate.bpmn")
                .deploy();
//        4.输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    @Test
    public void testStartProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        String key = "evection-candidate";

        runtimeService.startProcessInstanceByKey(key);
    }

    @Test
    public void findGroupTaskList(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        String candidateUser = "李四";

        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskCandidateUser(candidateUser)
                .list();

        for (Task task :
                list) {
            System.out.println("流程实例id=" + task.getProcessInstanceId());
            System.out.println("任务id=" + task.getId());
            System.out.println("任务负责人=" + task.getAssignee());
        }
    }

    /**
     * 候选人拾取任务
     */
    @Test
    public void clainTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        String candidateUser = "李四";

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskCandidateUser(candidateUser)
                .singleResult();

        String taskId = task.getId();

        if (task != null) {
            taskService.claim(taskId,candidateUser);
            System.out.println("taskId-" + taskId + "-用户-" + candidateUser + "拾取任务");
        }


    }

    /**
     * 归还任务
     */
    @Test
    public void assigneeToGroupTask(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        String taskId = "110002";

        String assignee = "李四";

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();

        if (task != null) {
            taskService.setAssignee(taskId,null);
            System.out.println("taskId-" + taskId + "-用户-" + assignee + "归还任务");
        }
    }

    /**
     * 交接任务，改变负责人
     */
    @Test
    public void testAssigneeToCandidateUser(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        String taskId = "110002";

        String assignee = "李四";

        String candidate = "王五";

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();

        if (task != null) {
            taskService.setAssignee(taskId,candidate);
            System.out.println("taskId-" + taskId + "-用户-" + assignee + "交接任务给" + candidate);
        }
    }

    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskAssignee("张三")
                .singleResult();

        if (task != null) {
            String taskId = task.getId();
            System.out.println("taskId="+taskId);
            taskService.complete(taskId);
        }
    }
}
