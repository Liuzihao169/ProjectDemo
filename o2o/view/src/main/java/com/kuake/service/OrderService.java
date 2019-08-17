package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Order;
public interface OrderService {
	// 添加订单信息
	void saveOrder(Order orderInfo) throws Exception;
	// 查看当前用户下的所有订单
	List<Order> findOrdersByUid(Long id) throws Exception;
}
