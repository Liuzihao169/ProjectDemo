package com.kuake.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuake.pojo.Order;
import com.kuake.service.OrderService;

/**
 * 订单web层
 * @author kk.菜鸟
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 查看所有用户的所有订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list(Model model) throws Exception{
		//调用service层，查询所用订单
		List<Order> orders=orderService.getAll();
		
		//将查询结果传回显示页面
		model.addAttribute("orders", orders);
		model.addAttribute("tabFlag", "2");
		return "index";
	}

}
