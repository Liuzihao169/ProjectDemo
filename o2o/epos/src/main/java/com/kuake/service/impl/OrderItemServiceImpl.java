package com.kuake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.OrderItemMapper;
import com.kuake.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemMapper orderItemMapper;
	

}
