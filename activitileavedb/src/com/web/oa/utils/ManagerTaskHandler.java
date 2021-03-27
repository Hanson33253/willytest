package com.web.oa.utils;



import javax.servlet.http.HttpSession;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.web.oa.pojo.Employee;
import com.web.oa.service.EmployeeService;

public class ManagerTaskHandler implements TaskListener{

	
	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		
		//通过硬编码获取spring容器
	   WebApplicationContext context= ContextLoader.getCurrentWebApplicationContext();
       EmployeeService employeeService	=	(EmployeeService) context.getBean("employeeService");

  //servlet api
    ServletRequestAttributes servletRequest=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpSession session=servletRequest.getRequest().getSession();
     Employee employee=  (Employee) session.getAttribute(Constants.GLOBAL_SESSION_ID);
		  Employee manager =   employeeService.findEmployeeByManagerId(employee.getManagerId());
		//设置代办人
		  task.setAssignee(manager.getName());
		  
		  
		  
	}
	
}
