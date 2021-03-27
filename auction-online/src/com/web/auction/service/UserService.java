package com.web.auction.service;

import com.web.auction.pojo.User;

public interface UserService {
	
	public User Login(String username,String password);
	public void addUser(User user);
	

}
