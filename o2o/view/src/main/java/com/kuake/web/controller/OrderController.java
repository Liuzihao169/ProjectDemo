package com.kuake.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuake.pojo.Order;
import com.kuake.pojo.User;
import com.kuake.service.OrderService;
import com.kuake.vo.OrderInfo;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/order-cart")
	public String orderCart(Order orderInfo,HttpServletRequest request){
		request.getSession().setAttribute("orderInfo", orderInfo);
		//把接收的消息存储在session中
		//获得项目名字
		String path = request.getContextPath();
		//重定向到跳转到填写配送信息
		//点击确认之后，跳转到订单列表详情页面。
		return "deliveryInfo";
	}
	
	//提价订单
	@RequestMapping("/orderinfo")
	public String order(Order order ,HttpServletRequest request) throws Exception{
		User user = (User) request.getSession().getAttribute("user");
		Order orderInfo = (Order) request.getSession().getAttribute("orderInfo");
		//页面传来配送信息
			/*
			 * 	tel: 订餐电话
			 *Name: 点餐人信息
			 *Address: 点餐人收货地址*/
			//简单封装orderInfo 封装user id
			orderInfo.setOrder_address(order.getOrder_address());
			orderInfo.setOrder_phone(order.getOrder_phone());
			orderInfo.setOrder_realname(order.getOrder_realname());
			orderInfo.setUser(user);
			orderService.saveOrder(orderInfo);
			//执行更新菜品的销量，剩余量。
			//重定向到订单列表详情页面。order-list
			// 执行清空购物车。
			
		return "redirect:/order/order-list.action";
	}
	
	//展示订单详情页面
	@RequestMapping("/order-list")
	public String showOrderList(HttpServletRequest request,Model model) throws Exception{
		//通过id查询该用户下的所有订单信息
		User user = (User) request.getSession().getAttribute("user");
		List<Order> list = orderService.findOrdersByUid(user.getUser_id());
		// 将信息存储到model当中，转发到页面
		model.addAttribute("orderList", list);
		return "historyOrder";
	}
}
