package com.web.oa.service;

import com.web.oa.pojo.Employee;

public interface EmployeeService {
	
	
	//�����û��������û�
	public Employee findEmployeeByUsername(String username);
	//��ѯ��ǰ��¼�˵��ϼ�����
	public Employee findEmployeeByManagerId(Long manager_id);
	

}
