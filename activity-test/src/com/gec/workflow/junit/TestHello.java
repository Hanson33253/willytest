package com.gec.workflow.junit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestHello {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//内部加载 activiti.cfg.xml
//	deployment  procedef bytearray
	@Test//1部署流程定义  相当于流程模板
public void testDeployProcess(){
	Deployment deployment=processEngine.getRepositoryService()
		            .createDeployment()
		            .name("hello")
		            .addClasspathResource("diagram/HelloProcess.bpmn")
		            .addClasspathResource("diagram/HelloProcess.png")
		            .deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
	}
	
	@Test //2启动流程实例  具体的某一个流程操作
	 //ru_ execution  ru_task
	public void testsstartProcess(){
		
		String key="HelloProcess";
		
		
	ProcessInstance pi=	processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
		
	}
	
	
//	@Test
//	//3查看我的待办事务
//	public void test(){
//		String assignee="jack";
//		
//	List<Task>list=processEngine.getTaskService()
//		    .createTaskQuery()
//		    .taskAssignee(assignee)
//		    .list();
//	for(Task task:list){
//		System.out.println(task.getId());
//		System.out.println(task.getAssignee());
//		System.out.println(task.getProcessDefinitionId());//流程定义的id
//		System.out.println(task.getProcessInstanceId());//流程实例的id
//	}
//	
//		    
//		    
//	}
	
//	
//	@Test
//	//3查看我的待办事务
//	public void test2(){
//		String assignee="danny";
//		
//	List<Task>list=processEngine.getTaskService()
//		    .createTaskQuery()
//		    .taskAssignee(assignee)
//		    .list();
//	for(Task task:list){
//		System.out.println(task.getId());
//		System.out.println(task.getAssignee());
//		System.out.println(task.getProcessDefinitionId());//流程定义的id
//		System.out.println(task.getProcessInstanceId());//流程实例的id
//	}
//	}
	
	@Test
	//3查看我的待办事务
	public void test3(){
		String assignee="boss";
		
	List<Task>list=processEngine.getTaskService()
		    .createTaskQuery()
		    .taskAssignee(assignee)
		    .list();
	for(Task task:list){
		System.out.println(task.getId());
		System.out.println(task.getAssignee());
		System.out.println(task.getProcessDefinitionId());//流程定义的id
		System.out.println(task.getProcessInstanceId());//流程实例的id
	}
	}
	
	
	
//	@Test //4task finished
//	public void testTaskFinished(){
//		String taskId="104";
//		
//		processEngine.getTaskService().complete(taskId);
//		
//		System.out.println("任务结束");
//		
//	}
	@Test //4task finished
	public void testTaskFinished2(){
		String taskId="202";
		
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("任务结束");
		
	
	
	}
	
	@Test //4task finished
	public void testTaskFinished3(){
		String taskId="1802";
		
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("任务结束");
		
	
	
	}
	

	@Test
	//5查询流程定义列表
	public void testFindProcessDeflist(){
	  List<ProcessDefinition> list=   processEngine.getRepositoryService()
	                                     .createProcessDefinitionQuery()
	                                     .list();
	   
	  
	  for(ProcessDefinition pd:list){
		  System.out.println(pd.getDeploymentId());
		  System.out.println(pd.getResourceName());
		  System.out.println(pd.getVersion());
		  System.out.println(pd.getKey());
		  
	  }
	}
	
	@Test
	//6删除流程定义
	public void testRemoveProcess(){
		String deploymentId="1501";
		
		processEngine.getRepositoryService()
		.deleteDeployment(deploymentId,true);
		//true代表强行删除，如果当前有流程定义的流程实例活动的照删
		System.out.println("删除完成");
	}
	
	@Test//7. 查看流程定义图
	public void testViewPic() throws IOException{
		String deploymentId="1501";
		String resourceName="diagram/HelloProcess.png";
	
InputStream input	=	processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);


File targetFile=new File("d://"+resourceName);

	FileUtils.copyInputStreamToFile(input, targetFile);
	System.out.println("操作完成");
	}
	
	@Test
	//8.判断流程实例是否结束
	public void testProcessInstanceEnd(){
		
		String processId="1601";
		
ProcessInstance	pi=	processEngine.getRuntimeService()
		.createProcessInstanceQuery()
		.processInstanceId(processId)
		.singleResult();
if(pi!=null){
	System.out.println("流程正在运行！");
}else{
	System.out.println("流程已经end");
}
	}
	
	
}
