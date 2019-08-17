package com.kuake.dao;


import com.kuake.pojo.User;

public interface UserMapper {
	
	User findUserByNameAndPassword(String user_name,String user_password) throws Exception;
	User findUserByUsername(String username);
	void saveUser(User user);
	
}
