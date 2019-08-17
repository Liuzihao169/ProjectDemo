package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Dict;


public interface DictService {
	public List<Dict> findDictByCode(String dict_code) throws Exception;

	public List<Dict> findAll() throws Exception;

	public boolean save(Dict dict) throws Exception;

	public boolean delete(Long dict_id) throws Exception;

}
