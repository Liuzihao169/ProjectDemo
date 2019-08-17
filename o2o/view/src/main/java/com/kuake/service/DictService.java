package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Dict;


public interface DictService {
	public List<Dict> findDictByCode(String dict_code) throws Exception;

}
