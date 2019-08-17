package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Greens;
import com.kuake.utils.EposResult;

public interface CartService {
	//Cart getCartWithUerId(Long id);
	void addGreensToCart(Long uId,Long greenId,Integer num) throws Exception;
	
	/**
	 * @Description:合并购物车，并且把合并之后的购物车添加到数据库，然后并且返回list 转发到逻辑视图渲染
	 * @param uId
	 * @param list
	 * @return
	 * @throws Exception
	 * @date: 2019年3月13日 下午4:38:42 
	 *
	 */
	List<Greens> mergeCart(Long uId,List<Greens>list) throws Exception;
	
	void updateCartGreenNum(Long uId,Long num,Long greenId) throws Exception;
	
	EposResult cleanCartByUid(Long uid) throws Exception;
}
