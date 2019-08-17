package com.kuake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.DictMapper;
import com.kuake.dao.GreensMapper;
import com.kuake.pojo.Dict;
import com.kuake.pojo.Greens;
import com.kuake.service.GreensService;
import com.kuake.vo.Vo;

@Service
public class GreensServiceImpl implements GreensService {
	@Autowired
	private GreensMapper greensMapper;
	@Autowired
	private DictMapper dictMapper;

	@Override
	public Vo findByType(Vo vo) throws Exception {
		List<Greens> greensList=greensMapper.findGreensByType(vo.getDict_type_code());
		List<Dict> greensLevels=dictMapper.findDictByCode(vo.getDict_code());
		
		vo.setGreensList(greensList);
		vo.setGreensLevels(greensLevels);
		
		return vo;
	}

	@Override
	public boolean deleteByVo(Vo vo) throws Exception {
		return greensMapper.deleteByVo(vo);
	}

	@Override
	public Vo findById(Vo vo) throws Exception {
		Long greens_id=vo.getGreens_id();
		String dict_code=vo.getDict_code();
		
		Greens greens=greensMapper.findGreesById(greens_id);
		List<Dict> greensLevels=dictMapper.findDictByCode(dict_code);
		
		vo.setGreens(greens);
		vo.setGreensLevels(greensLevels);
		return vo;
	}

	@Override
	public boolean update(Greens greens) throws Exception {
		return greensMapper.updateGreens(greens);
	}

	@Override
	public List<Dict> getGreensType(String dict_code) throws Exception {
		return dictMapper.findDictByCode(dict_code);
	}

	@Override
	public boolean save(Greens greens) throws Exception {
		return greensMapper.addGreens(greens);
	}
}
