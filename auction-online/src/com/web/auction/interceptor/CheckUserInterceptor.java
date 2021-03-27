package com.web.auction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckUserInterceptor implements HandlerInterceptor {

	//执行handler之前拦截

	//true:放行,   进入到这个handler里面来 > 执行handler的核心代码》return >afterCompletion
	//false：不放行，不会执行handler
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
	String path=	request.getRequestURI();
	System.out.println(path);
		
		if(path.indexOf("doLogin")!=-1||path.indexOf("doRegister")!=-1){
			
			return true;//两个其中一个都不要拦截
			
		}
		if(session.getAttribute("user")!=null){
			return true;
			
		}else{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		return false;}
	}
	
	
	//执行handler之后做拦截  并且在执行return之前执行返回
	//应用场景:业务规则跳转 需要根据一定的修改
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	//handler之后
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
