package com.kuake.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuake.service.OrderItemService;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;

}
