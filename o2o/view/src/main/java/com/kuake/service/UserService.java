package com.kuake.service;

import com.kuake.pojo.User;
import com.kuake.utils.EposResult;

public interface UserService {
	public boolean Longin(User user) throws Exception;
	EposResult login(User user) throws Exception;
	EposResult checkUserName(String userName) throws Exception;
	EposResult saveUser(User user)throws Exception;
}
