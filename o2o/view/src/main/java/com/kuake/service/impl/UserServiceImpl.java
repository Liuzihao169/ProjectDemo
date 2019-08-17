package com.kuake.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.UserMapper;
import com.kuake.pojo.User;
import com.kuake.service.UserService;
import com.kuake.utils.EposResult;
import com.kuake.utils.IDUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean Longin(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EposResult login(User user) throws Exception {
		User u = userMapper.findUserByNameAndPassword(user.getUser_name(),user.getUser_password());
		if(u==null){
			//如果用户不存在，那么返回用户名密码错误。错误信息 500
			return EposResult.build(500, "用户名或密码不正确");
		}
			return EposResult.build(200,"成功登陆", u);
	}

	@Override
	public EposResult checkUserName(String userName) throws Exception {
		User user = userMapper.findUserByUsername(userName);	
		if(user!=null){
			return EposResult.build(500, "用户名已经存在");
		}
		return EposResult.ok();
	}
	
	//保存用户
	@Override
	public EposResult saveUser(User user) throws Exception {
		//封装数据的完整性
		long genUserId = IDUtils.genUserId();
		user.setUser_id(genUserId);
		user.setUser_level("2");
		userMapper.saveUser(user);
		return EposResult.ok();
	}
	


}
