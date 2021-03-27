package com.web.auction.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.auction.pojo.User;
import com.web.auction.service.UserService;




@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/doLogin")
	public String doLogin(String username, String userpassword, String inputCode, HttpSession session, Model model) {

		// 1.�ж���֤��

		String numrand = (String) session.getAttribute("numrand");
		if (!inputCode.equals(numrand)) {
			model.addAttribute("errorMsg", "��֤�����!");
			return "login";

		}

		// 2.ҵ���ж�
		User user = userService.Login(username, userpassword);

		if (user!= null) {
			session.setAttribute("user", user);

			return "redirect:/queryAuctions";

		} else {
			model.addAttribute("errorMsg", "�ʺŻ����������");

			return "login";

		}

	}
	
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpSession session){
		session.invalidate();
		
		
		return"login";
		
		
	}
	
	
	//Bindingresult bindingresultҪ���ڴ�ż����pojo����ĺ��棬��Ŵ�����Ϣ������.
	@RequestMapping("/ doRegister")
	public String doRegister( Model model,@ModelAttribute("registerUser") @Validated User user
			,BindingResult bindingResult){
		//1.�ж��Ƿ�ͨ��
		if(bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
		
		for(FieldError error:errors){
			
			model.addAttribute( error.getField()  ,error.getDefaultMessage());
			
			
		}
		return"register";
		
		}
		
		
		//2 �������ݵ�mysql
		
		userService.addUser(user);
		return "login";
		
		
	}

}
