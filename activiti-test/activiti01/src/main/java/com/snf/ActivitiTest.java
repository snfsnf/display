package com.snf;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

public class ActivitiTest {

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
                .name("出差申请流程")
                .addClasspathResource("bpmn/evection.bpmn20.xml")
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
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");
//        4.输出内容
        System.out.println("流程定义id=" + instance.getProcessDefinitionId());
        System.out.println("流程实例id=" + instance.getId());
        System.out.println("当前活动id=" + instance.getActivityId());

    }

    /**
     * 查询个人待执行任务
     */
    @Test
    public void testFindPersonalTaskList(){
//        1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取taskService
        TaskService taskService = defaultProcessEngine.getTaskService();
//        3.根据流程key和任务负责人查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("zhangsan")
                .list();
//        4.输出
        for (Task task: taskList) {
            System.out.println("流程实例id=" + task.getProcessInstanceId());
            System.out.println("任务id=" + task.getId());
            System.out.println("任务负责人=" + task.getAssignee());
            System.out.println("任务名称=" + task.getName());
        }

    }

    /**
     * 完成任务
     */
    @Test
    public void testCompleteTask(){
//        1.获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        2.获取taskService
        TaskService taskService = defaultProcessEngine.getTaskService();
//        3.根据任务id完成任务
        taskService.complete("10005");
    }

    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition processDefinition :
                definitionList) {
            System.out.println("流程定义id=" + processDefinition.getId());
            System.out.println("流程定义name=" + processDefinition.getName());
            System.out.println("流程定义key=" + processDefinition.getKey());
            System.out.println("流程定义版本=" + processDefinition.getVersion());
            System.out.println("====================");
        }
    }

    /**
     * 删除流程部署信息
     */
    @Test
    public void deleteDeployment(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

//        repositoryService.deleteDeployment("1");
//        级联删除，删除正在执行的
        repositoryService.deleteDeployment("1",true);

    }

    /**
     * 下载资源文件
     */
    @Test
    public void getDeployment() throws IOException {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .processDefinitionVersion(2)
                .singleResult();

        String deploymentId = processDefinition.getDeploymentId();

        String bpmnName = processDefinition.getResourceName();

        InputStream bpmnInputStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);

        File bpmnFile = new File("E:\\display\\evection1.bpmn20.xml");

        FileOutputStream bpmnOutputStream = new FileOutputStream(bpmnFile);

        IOUtils.copy(bpmnInputStream,bpmnOutputStream);

        bpmnOutputStream.close();

        bpmnInputStream.close();

    }

    /**
     * 查看历史信息
     */
    @Test
    public void queryHistoryInfo(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        HistoryService historyService = defaultProcessEngine.getHistoryService();

        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        //查询 act_hi_actinst表
        instanceQuery.processInstanceId("10001");

        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();

        List<HistoricActivityInstance> list = instanceQuery.list();

        for (HistoricActivityInstance hi : list) {
            System.out.println(hi.getActivityId());
            System.out.println(hi.getActivityName());
            System.out.println(hi.getProcessDefinitionId());
            System.out.println(hi.getProcessInstanceId());
            System.out.println(hi.getAssignee());
            System.out.println("==========================");
        }
    }
}
