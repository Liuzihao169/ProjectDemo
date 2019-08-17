package com.kuake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.DictMapper;
import com.kuake.pojo.Dict;
import com.kuake.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<Dict> findDictByCode(String dict_code) throws Exception {
		return dictMapper.findDictByCode(dict_code);
	}

	@Override
	public List<Dict> findAll() throws Exception {
		return dictMapper.findAll();
	}

	@Override
	public boolean save(Dict dict) throws Exception {
		return dictMapper.addDict(dict);
	}

	@Override
	public boolean delete(Long dict_id) throws Exception {
		return dictMapper.deleteDict(dict_id);
	}
	

}
