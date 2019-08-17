package com.kuake.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.kuake.pojo.Greens;
import com.kuake.pojo.User;
import com.kuake.service.CartService;
import com.kuake.service.GreensService;
import com.kuake.service.OrderItemService;
import com.kuake.utils.CookieUtils;
import com.kuake.utils.EposResult;
import com.kuake.utils.JsonUtils;
import com.kuake.vo.Cart;
import com.kuake.vo.EPOSconstant;

@Controller
@RequestMapping("/cart")
public class CartController {

	// 购物车过期时间 应该从配置文件当中加载，问题：无法从配置文件当中取值，已经添加读改配置文件
	// @Value("${CART_COOKIE_TIMEOUT}")
	// 默认保存三天
	@Autowired
	private GreensService greensService;
	@Autowired
	private CartService cartService;

	// 添加green到购物车。
	@ResponseBody
	@RequestMapping("/{greenId}")
	public EposResult addGreenToCart(Integer num, @PathVariable Long greenId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 分析：要先判断用户是否登陆
		Object object = request.getSession().getAttribute("user");
		// 1.如果未登陆执行上面的业务逻辑，
		if (object == null) {
			// 1.从cookie当中取出商品列表
			List<Greens> greenListFromCookie = getGreenListFromCookie(request, "cart");
			// 设置标识符
			boolean flag = false;
			// 2.判断列表是否包含改商品
			for (Greens greens : greenListFromCookie) {
				// 3.如果含有，商品数目相加
				if (greens.getGreens_id() == greenId.longValue()) {
					greens.setGreens_count(greens.getGreens_count().longValue() + num);
					flag = true;
					break;
				}
			}
			if (!flag) {
				// 4.没有，根据商品id 查询商品的具体信息
				Greens greenById = greensService.findGreenById(greenId);
				// 更改count字段作为储存 num 的数量
				greenById.setGreens_count(num.longValue());
				// 5.将改商品也存储在cookie当中
				greenListFromCookie.add(greenById);
			}

			CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(greenListFromCookie),
					EPOSconstant.CART_TIME_OUT, true);
			return EposResult.build(200, "成功添加到购物车");
		}
		// 2.如果是登陆状态，从数据库取出购物车列表
		User user = (User) object;
		// 执行添加到服务器端的操作。
		cartService.addGreensToCart(user.getUser_id(), greenId, num);
		return EposResult.build(200, "成功添加到购物车");
	}

	// 展示购物车列表 菜篮子列表
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		List<Greens> list = getGreenListFromCookie(request, "cart");
		// 1.判断用户是否登录，
		User user = (User) request.getSession().getAttribute("user");
		// 2.未登录状态，直接从cookies当中取商品列表，添加model当中，返回逻辑视图
		if (user != null) {
			// 3.登录状态，购物车信息应该是从服务端取。
			// 4.合并购物车：判断cookies当否为空，不为空将cookies当中的商品列表与存在数据库的购物车列表合并。并且将购物车信息存储在数据库。并且返回合并之后的list
			list = cartService.mergeCart(user.getUser_id(), list);
			// 5.删除cookies当中的购物车信息。
			CookieUtils.deleteCookie(request, response, "cart");
		}
		// 7.将购物车信息存在model当中，返回逻辑视图。
		model.addAttribute("cartList", list);
		return "cart";
	}

	// 修改购车 菜篮子
	@RequestMapping("/update/num/{greenId}/{num}")
	public void updateCartNum(@PathVariable Long greenId, @PathVariable Long num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			// 3.登录状态，直接从当中取出存储的购物车信息，遍历购物车，更改对应商品的id,然后存储在数据库当中，
			cartService.updateCartGreenNum(user.getUser_id(), num, greenId);
			return;
		}
		// 1.判断用户是否登录，
		// 2.如果是未登录状态，直接从cookies当中取字段 CART，然后根据商品id修改商品数量。
		List<Greens> list = getGreenListFromCookie(request, "cart");
		for (Greens greens : list) {
			if (greens.getGreens_count().longValue() == greenId) {
				// 更新数目
				greens.setGreens_count(num);
				break;
			}
		}
		// 写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(list), EPOSconstant.CART_TIME_OUT,
				true);
	}

	// 清空购物车
	@RequestMapping("/clean")
	@ResponseBody
	public EposResult cleanCart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			// 未登入状态，直接清空本地购物车
			CookieUtils.deleteCookie(request, response, "cart");
			return EposResult.ok();
		}
		EposResult result = cartService.cleanCartByUid(user.getUser_id());
		return result;
	}
	// 从cookies当中获得购物车数据的值
	private List<Greens> getGreenListFromCookie(HttpServletRequest request, String cookieName) {
		String json = CookieUtils.getCookieValue(request, cookieName, true);
		if (StringUtils.isNotBlank(json)) {
			List<Greens> list = JsonUtils.jsonToList(json, Greens.class);
			return list;
		}
		return new ArrayList<Greens>();
	}
	
	

}
