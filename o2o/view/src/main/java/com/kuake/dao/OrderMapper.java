package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.Order;
import com.kuake.vo.OrderInfo;

public interface OrderMapper  {
	void saveOrder(Order order) throws Exception; 
	//查询所有订单
	List<Order> findOrderListByUid(Long id) throws Exception;
}
