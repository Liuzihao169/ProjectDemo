package com.kuake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.GreensMapper;
import com.kuake.pojo.Greens;
import com.kuake.service.GreensService;
import com.kuake.vo.Category;

@Service
public class GreensServiceImpl implements GreensService {
	@Autowired
	private GreensMapper greensMapper;
	
	//根据code到数据字典当中查出商品分类的列表
	@Override
	public List<Category> findCategoryByCode(String code) throws Exception {
		
		return greensMapper.findCategoryByCode(code);
	}

	/**
	 *
	 * @Description:
	 * @param typeCode 商品类型
	 * @return  
	 * @return 
	 * @throws Exception 
	 * @date: 2019年3月12日 下午7:43:28 
	 *
	 */
	@Override
	public List<Greens> findGreensListByTypeCode(Long typeCode) throws Exception {
		return greensMapper.findGreensListByTypeCode(typeCode);
	}

	@Override
	public Greens findGreenById(Long id) throws Exception {
		return greensMapper.findGreenById(id);
	}
	
}
