package com.kuake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.UserMapper;
import com.kuake.pojo.User;
import com.kuake.service.UserService;
import com.kuake.vo.Vo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByVo(Vo vo) throws Exception {
		return userMapper.findUserByVo(vo);
	}

	@Override
	public List<User> findAll() throws Exception {
		return userMapper.findAll();
	}

	@Override
	public boolean delete(Long user_id) throws Exception {
		return userMapper.delete(user_id);
	}
	


}
