package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.Greens;
import com.kuake.vo.Category;

public interface GreensMapper {
	/**
	 * @Description:根据code查找分类列表
	 * @param code
	 * @return
	 * @throws Exception
	 * @date: 2019年3月12日 下午7:35:23 
	 *
	 */
	List<Category> findCategoryByCode(String  code) throws Exception;
	
	
	/**
	 * @Description:跟个分类code查找同一类商品
	 * @param typeCode
	 * @return
	 * @throws Exception
	 * @date: 2019年3月12日 下午7:35:08 
	 *
	 */
	List<Greens> findGreensListByTypeCode(Long typeCode) throws Exception;
	
	Greens findGreenById(Long id) throws Exception;
	void updateGreensNum(Integer orderitem_count,Long greenId);
}
