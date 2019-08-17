package com.kuake.dao;

import com.kuake.dao.BaseUserMapper;
import com.kuake.pojo.User;
import com.kuake.vo.Vo;

public interface UserMapper extends BaseUserMapper {

	public User findUserByVo(Vo vo) throws Exception;

	public boolean delete(Long user_id) throws Exception;

}
