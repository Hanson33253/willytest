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
	//�ڲ����� activiti.cfg.xml
//	deployment  procedef bytearray
	@Test//1�������̶���  �൱������ģ��
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
	
	@Test //2��������ʵ��  �����ĳһ�����̲���
	 //ru_ execution  ru_task
	public void testsstartProcess(){
		
		String key="HelloProcess";
		
		
	ProcessInstance pi=	processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
		
	}
	
	
//	@Test
//	//3�鿴�ҵĴ�������
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
//		System.out.println(task.getProcessDefinitionId());//���̶����id
//		System.out.println(task.getProcessInstanceId());//����ʵ����id
//	}
//	
//		    
//		    
//	}
	
//	
//	@Test
//	//3�鿴�ҵĴ�������
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
//		System.out.println(task.getProcessDefinitionId());//���̶����id
//		System.out.println(task.getProcessInstanceId());//����ʵ����id
//	}
//	}
	
	@Test
	//3�鿴�ҵĴ�������
	public void test3(){
		String assignee="boss";
		
	List<Task>list=processEngine.getTaskService()
		    .createTaskQuery()
		    .taskAssignee(assignee)
		    .list();
	for(Task task:list){
		System.out.println(task.getId());
		System.out.println(task.getAssignee());
		System.out.println(task.getProcessDefinitionId());//���̶����id
		System.out.println(task.getProcessInstanceId());//����ʵ����id
	}
	}
	
	
	
//	@Test //4task finished
//	public void testTaskFinished(){
//		String taskId="104";
//		
//		processEngine.getTaskService().complete(taskId);
//		
//		System.out.println("�������");
//		
//	}
	@Test //4task finished
	public void testTaskFinished2(){
		String taskId="202";
		
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("�������");
		
	
	
	}
	
	@Test //4task finished
	public void testTaskFinished3(){
		String taskId="1802";
		
		processEngine.getTaskService().complete(taskId);
		
		System.out.println("�������");
		
	
	
	}
	

	@Test
	//5��ѯ���̶����б�
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
	//6ɾ�����̶���
	public void testRemoveProcess(){
		String deploymentId="1501";
		
		processEngine.getRepositoryService()
		.deleteDeployment(deploymentId,true);
		//true����ǿ��ɾ���������ǰ�����̶��������ʵ�������ɾ
		System.out.println("ɾ�����");
	}
	
	@Test//7. �鿴���̶���ͼ
	public void testViewPic() throws IOException{
		String deploymentId="1501";
		String resourceName="diagram/HelloProcess.png";
	
InputStream input	=	processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);


File targetFile=new File("d://"+resourceName);

	FileUtils.copyInputStreamToFile(input, targetFile);
	System.out.println("�������");
	}
	
	@Test
	//8.�ж�����ʵ���Ƿ����
	public void testProcessInstanceEnd(){
		
		String processId="1601";
		
ProcessInstance	pi=	processEngine.getRuntimeService()
		.createProcessInstanceQuery()
		.processInstanceId(processId)
		.singleResult();
if(pi!=null){
	System.out.println("�����������У�");
}else{
	System.out.println("�����Ѿ�end");
}
	}
	
	
}
