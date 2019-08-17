package com.kuake.dao;

import com.kuake.vo.Cart;

public interface CartMapper {
	//根据用ID从购物里取出购物车信息
	Cart getCartWithUerId(Long id);
	
	/**
	 * @Description:把商品添加到服务器端
	 * @param uid
	 * @param jsonText
	 * @return
	 * @date: 2019年3月13日 下午3:27:11 
	 *
	 */
	void addGreenToCartWithUid(Cart cart) throws Exception;
	void UpdateCart(Cart cart) throws Exception;
	// 清空购物车
	void deleteCartByUid(Long id) throws Exception;
}
