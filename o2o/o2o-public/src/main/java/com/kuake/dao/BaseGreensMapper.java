package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.Greens;

public interface BaseGreensMapper {
	/**
	 * 查询所有菜品信息
	 * @return List<Greens>
	 */
	public List<Greens> findAll() throws Exception;
	
	/**
	 * 通过菜品类型查询菜品
	 * @param greens_type 菜品类型
	 * @return List<Greens>
	 */
	public List<Greens> findGreensByType(String greens_type) throws Exception;
	
	/**
	 * 通过菜品Id查询菜品
	 * @param greens_id 菜品ID
	 * @return Greens
	 */
	public Greens findGreesById(Long greens_id) throws Exception;
	
	/**
	 * 添加一条菜品记录
	 * @param greens Greens
	 * @return false and true
	 */
	public boolean addGreens(Greens greens) throws Exception;
	
	/**
	 * 更新菜品信息
	 * @param greens Greens
	 * @return false and true
	 */
	public boolean updateGreens(Greens greens) throws Exception;
	
	/**
	 * 通过菜品ID删除一条菜品信息
	 * @param greens_id 菜品id
	 * @return false and true
	 */
	public boolean deleteGreens(Long greens_id) throws Exception;

}
