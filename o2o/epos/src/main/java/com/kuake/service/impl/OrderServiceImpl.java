package com.kuake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.OrderMapper;
import com.kuake.pojo.Order;
import com.kuake.service.OrderService;

/**
 * 订单业务层
 * @author kk.菜鸟
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 获取所有订单
	 */
	@Override
	public List<Order> getAll() throws Exception {
		return orderMapper.findAll();
	}
	

}
