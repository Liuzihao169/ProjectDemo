package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Dict;
import com.kuake.pojo.Greens;
import com.kuake.vo.Vo;

public interface GreensService {

	Vo findByType(Vo vo) throws Exception;

	boolean deleteByVo(Vo vo) throws Exception;

	Vo findById(Vo vo) throws Exception;

	boolean update(Greens greens) throws Exception;

	List<Dict> getGreensType(String greensType) throws Exception;

	boolean save(Greens greens) throws Exception;

}
