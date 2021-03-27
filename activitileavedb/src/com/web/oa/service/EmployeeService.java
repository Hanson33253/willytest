package com.web.oa.service;

import com.web.oa.pojo.Employee;

public interface EmployeeService {
	
	
	//根据用户名查找用户
	public Employee findEmployeeByUsername(String username);
	//查询当前登录人的上级主管
	public Employee findEmployeeByManagerId(Long manager_id);
	

}
