package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.Dict;

public interface BaseDictMapper {
	/**
	 * 查询所有字典表信息
	 * @return
	 */
	public List<Dict> findAll() throws Exception;
	
	/**
	 * 通过字典编号查询字典表，返回Dictd对象的List集合
	 * @param dict_code 字典编号
	 * @return Dictd对象的List集合
	 */
	public List<Dict> findDictByCode(String dict_code) throws Exception;
	
	/**
	 * 通过字典表ID查询
	 * @param dict_id 字典表ID
	 * @return Dict对象
	 */
	public Dict findDictById(Long dict_id) throws Exception;
	
	/**
	 * 向字典表添加一条记录
	 * @param dict
	 * @return
	 */
	public boolean addDict(Dict dict) throws Exception;
	
	/**
	 * 更新字典表的一条记录
	 * @param dict
	 * @return
	 */
	public boolean updateDict(Dict dict) throws Exception;
	
	/**
	 * 通过字典表ID删除一条记录
	 * @param dict_id
	 * @return
	 */
	public boolean deleteDict(Long dict_id) throws Exception;

}
