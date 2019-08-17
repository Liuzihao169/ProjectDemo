package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.User;

public interface BaseUserMapper {
	/**
	 * 查询所有用户
	 * @return List<User>
	 */
	public List<User> findAll() throws Exception;
	
	/**
	 * 通过用户级别查询用户
	 * @param user_level 字段-用户级别
	 * @return List<User>
	 */
	public List<User> findUserByLevel(String user_level) throws Exception;
	
	/**
	 * 通过用户账号和密码查询用户
	 * @param user_name 用户账号
	 * @param user_password 用户密码
	 * @return User
	 */
	public User findUserByNameAndPassword(String user_name,String user_password) throws Exception;
	
	/**
	 * 通过用户ID查询用户
	 * @param user_id 用户ID
	 * @return User
	 */
	public User findUserById(Long user_id) throws Exception;
	
	/**
	 * 添加一个用户
	 * @param user User类型-参数
	 * @return false and true
	 */
	public boolean addUser(User user) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user User类型-参数
	 * @return false and true
	 */
	public boolean updateUser(User user) throws Exception;
	
	/**
	 * 通过用户ID删除一个用户
	 * @param user_id 用户ID
	 * @return false and true
	 */
	public boolean deleteUser(Long user_id) throws Exception;
}
