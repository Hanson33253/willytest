package com.web.oa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.oa.pojo.Employee;
import com.web.oa.service.EmployeeService;
import com.web.oa.utils.Constants;


@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping("/login")
	public String login(String username,String password,Model model,HttpSession session){
		Employee loginEmp=employeeService.findEmployeeByUsername(username);
		if(loginEmp!=null){
			if(loginEmp.getPassword().equals(password)){
				session.setAttribute(Constants.GLOBAL_SESSION_ID, loginEmp);
				return "index";
				
			}else{
				model.addAttribute("errorMsg", "’ ∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
				return "login";
				
			}
		}else{
			model.addAttribute("errorMsg", "’ ∫≈ªÚ√‹¬Î¥ÌŒÛ");
			return "login";
			
		}

		
	}
	
	
    @RequestMapping("/logout")
 public String logout(HttpSession session){
 	session.invalidate();
       return "login";

}

}
