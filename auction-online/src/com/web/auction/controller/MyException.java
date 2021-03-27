package com.web.auction.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.web.auction.utils.AuctionPriceException;

@ControllerAdvice//集中处理系统异常
public class MyException {
	
	@ExceptionHandler(AuctionPriceException.class)
public ModelAndView priceException(Exception e){
	ModelAndView mv=new ModelAndView();
	mv.addObject("errorMsg", e.getMessage());
	mv.setViewName("error");
	return mv;
	
	
}

}
