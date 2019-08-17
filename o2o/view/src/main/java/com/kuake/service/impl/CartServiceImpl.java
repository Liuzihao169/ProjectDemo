package com.kuake.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.security.XBeanAuthorizationEntry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.CartMapper;
import com.kuake.pojo.Greens;
import com.kuake.service.CartService;
import com.kuake.service.GreensService;
import com.kuake.utils.EposResult;
import com.kuake.utils.JsonUtils;
import com.kuake.vo.Cart;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private GreensService greensService;
	/*
	 * @Override public Cart getCartWithUerId(Long id) { return
	 * cartMapper.getCartWithUerId(id); }
	 */

	public void addGreensToCart(Long uId, Long greenId, Integer num) throws Exception {
		// 根据uid查找出购物车的内容
		Cart cart = cartMapper.getCartWithUerId(uId);
		// 根据greendId查找对应的green
		Greens greenById = greensService.findGreenById(greenId);
		// 更改count字段的数据为 购物车的数量
		greenById.setGreens_count(num.longValue());
		// 设置标志符号
		boolean flag = false;// 如果为空，那么与数据库当中存在时一样的效果，都要进行gid查询然后存储到数据当中。
		// 第一次添加购物车，
		if (cart == null) {
			List<Greens> cart4Fist = new ArrayList<>();
			// 创建购物车
			Cart fistCart = new Cart();
			cart4Fist.add(greenById);
			fistCart.setCart_desc(JsonUtils.objectToJson(cart4Fist));
			fistCart.setCart_id(uId);
			cartMapper.addGreenToCartWithUid(fistCart);
			return;
		}
		// 购物车字段存在且不为空的情况下
		String cart_desc = cart.getCart_desc();
		List<Greens> jsonToList = JsonUtils.jsonToList(cart_desc, Greens.class);
		// //3.判断该列表是否含有该商品，
		// 4.如果有，商品数目相加，
		// 5.如果没有添加该商品。
		for (Greens greens : jsonToList) {
			if (greens.getGreens_id().longValue() == greenId) {
				greens.setGreens_count(greens.getGreens_count().longValue() + num);
				// 更新到数据库
				flag = true;
				break;
			}
		}
		if (!flag) {
			jsonToList.add(greenById);
			// 更新数据库
		}
		cart.setCart_desc(JsonUtils.objectToJson(jsonToList));
		cartMapper.UpdateCart(cart);
		// 将数据存储到数据当中。
		/*
		 * Cart newCart = new Cart(); newCart.setCart_id(uId);
		 */

	}

	@Override
	public List<Greens> mergeCart(Long uId, List<Greens> list) throws Exception {
		// 临时区域
		List<Greens> listTemp = new ArrayList<>();
		// 从数据当中获得购物车
		Cart cartWithUerId = cartMapper.getCartWithUerId(uId);
		// 先声明
		if (cartWithUerId == null) {
			// 如果数据库购物车为空，那么返回他自己本身的list;
			// 然后将数据存储到数据库当中
			// 如果cookies当中含有list那么
			if (list.size() > 0) {
				cartWithUerId = new Cart();
				cartWithUerId.setCart_id(uId);
				cartWithUerId.setCart_desc(JsonUtils.objectToJson(list));
				cartMapper.addGreenToCartWithUid(cartWithUerId);
			}
			return list;
		}

		// 如果不为空，那么执行下面的操作。
		List<Greens> jsonToList = JsonUtils.jsonToList(cartWithUerId.getCart_desc(), Greens.class);
		if(list.size()==0){
			return jsonToList;
		}
		// 利用多层循环，检查是否含有相同类型的购物车。
		boolean flag = false;
		for (Greens greenFromCookie : list) {
			for (Greens greens : jsonToList) {
				if (greenFromCookie.getGreens_id().longValue() == greens.getGreens_id()) {
					// 如果id相同，那么同属于一个greens，合并数量
					greens.setGreens_count(
							greens.getGreens_count().longValue() + greenFromCookie.getGreens_count().longValue());
					// 跳出循环。
					flag = true;
					break;
				}
			}
			if (!flag) {
				// 那么就是数据库购物车不存在，就把这个greens存储到数据库当中。
				// 先暂时存储到一个临时的List当中，
				listTemp.add(greenFromCookie);
			}
		}
		// 遍历，把新的商品添加到list当中，然后存储到数据库
		if (listTemp.size() > 0) {
			for (Greens greens : listTemp) {
				jsonToList.add(greens);
			}
		}
		// 将修改后的购物车重新写入数据库
		cartWithUerId.setCart_desc(JsonUtils.objectToJson(jsonToList));
		// 跟新购物车
		cartMapper.UpdateCart(cartWithUerId);
		// 返回list
		return jsonToList;
	}

	// 登陆状态更新购物车的数量
	@Override
	public void updateCartGreenNum(Long uId, Long num, Long greenId) throws Exception {
		Cart cartWithUerId = cartMapper.getCartWithUerId(uId);
		List<Greens> list = new ArrayList<>();
		// 当数据库购物不为空的时候
		if (StringUtils.isNotBlank(cartWithUerId.getCart_desc())) {
			list = JsonUtils.jsonToList(cartWithUerId.getCart_desc(), Greens.class);
			for (Greens greens : list) {
				if (greens.getGreens_id().longValue() == greenId) {
					// 如果id相同，那么同属于一个greens，合并数量
					greens.setGreens_count(num);
					// 跳出循环。
					break;
				}
			}
			// 将修改后的购物车重新写入数据库
			cartWithUerId.setCart_desc(JsonUtils.objectToJson(list));
			// 跟新购物车
			cartMapper.UpdateCart(cartWithUerId);
		}
	}

	@Override
	public EposResult cleanCartByUid(Long uid) throws Exception {
		cartMapper.deleteCartByUid(uid);
		return EposResult.ok();
	}
}
