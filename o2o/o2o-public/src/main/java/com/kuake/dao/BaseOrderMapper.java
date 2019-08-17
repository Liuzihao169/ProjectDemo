package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.Order;

public interface BaseOrderMapper {
	/**
	 * 查询所有订单
	 * @return List<Order>
	 */
	public List<Order> findAll() throws Exception;
	
	/**
	 * 通过用户Id查询订单
	 * @param user_id 用户Id
	 * @return List<Order>
	 */
	public List<Order> findOrderByUserId(Long user_id) throws Exception;
	
	/**
	 * 通过订单号查询订单
	 * @param order_id 订单号
	 * @return Order
	 */
	public Order findOrderById(Long order_id) throws Exception;
	
	/**
	 * 添加订单
	 * @param order Order 
	 * @return false and true
	 */
	public boolean addOrder(Order order) throws Exception;
	
	/**
	 * 通过订单Id删除订单记录
	 * @param order_id
	 * @return false and true
	 */
	public boolean deleteOrder(Long order_id) throws Exception;

}
