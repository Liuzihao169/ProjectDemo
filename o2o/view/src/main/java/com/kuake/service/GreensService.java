package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Greens;
import com.kuake.vo.Category;

public interface GreensService {
	
	/**
	 * @author: Hao
	 * @Description:根据code查询数据字典的列表信息
	 * @throws：
	 * @param code
	 * @return 
	 * @throws Exception
	 * @date: 2019年3月12日 下午6:28:43 
	 *
	 */
	List<Category> findCategoryByCode(String code) throws Exception;
	
	/**
	 * @Description: 查询同一类商品列表
	 * @param typeCode
	 * @return 
	 * @throws Exception 
	 * @date: 2019年3月12日 下午7:43:02 
	 *
	 */
	List<Greens> findGreensListByTypeCode(Long typeCode) throws Exception;
	
	Greens findGreenById(Long id) throws Exception;
}
