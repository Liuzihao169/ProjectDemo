package com.kuake.service;

import java.util.List;

import com.kuake.pojo.User;
import com.kuake.vo.Vo;

public interface UserService {
	public User findUserByVo(Vo vo) throws Exception;

	public List<User> findAll() throws Exception;

	public boolean delete(Long user_id) throws Exception;

}
