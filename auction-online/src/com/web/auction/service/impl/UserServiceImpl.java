package com.web.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.web.auction.mapper.UserMapper;

import com.web.auction.pojo.User;
import com.web.auction.pojo.UserExample;
import com.web.auction.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper  userMapper;//代理接口
	

	@Override
	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		
		UserExample userExample=new UserExample();
		UserExample.Criteria criteria=userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		 
		criteria.andUserpasswordEqualTo(password);
		  List<User> list =userMapper.selectByExample(userExample);;
		
		  if(list!=null&&list.size()>0){
			  
			  return list.get(0);
			  
		  }
		
		return null;
	}


	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		user.setUserisadmin(0);
		userMapper.insert(user);
		
	}

}
