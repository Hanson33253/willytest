package com.web.auction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckUserInterceptor implements HandlerInterceptor {

	//ִ��handler֮ǰ����

	//true:����,   ���뵽���handler������ > ִ��handler�ĺ��Ĵ��롷return >afterCompletion
	//false�������У�����ִ��handler
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
	String path=	request.getRequestURI();
	System.out.println(path);
		
		if(path.indexOf("doLogin")!=-1||path.indexOf("doRegister")!=-1){
			
			return true;//��������һ������Ҫ����
			
		}
		if(session.getAttribute("user")!=null){
			return true;
			
		}else{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		return false;}
	}
	
	
	//ִ��handler֮��������  ������ִ��return֮ǰִ�з���
	//Ӧ�ó���:ҵ�������ת ��Ҫ����һ�����޸�
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	//handler֮��
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
