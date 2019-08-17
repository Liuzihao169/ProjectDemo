package com.kuake.dao;

import java.util.List;

import com.kuake.pojo.OrderItem;

public interface BaseOrderItemMapper {
	/**
	 * 通过订单id查询订单项
	 * @param order_id 订单Id
	 * @return List<OrderItem>
	 */
	public List<OrderItem> findOrderItemByOrderId(Long order_id) throws Exception;
	
	/**
	 * 通过订单项id查询订单项
	 * @param orderItem_id 订单项Id
	 * @return
	 */
	public OrderItem findOrderItemById(Long orderItem_id) throws Exception;
	
	/**
	 * 添加订单项
	 * @param orderItem OrderItem
	 * @return false and true
	 */
	public boolean addOrderItem(OrderItem orderItem) throws Exception;
	
	/**
	 * 通过订单Id删除订单项
	 * @param order_id 订单id
	 * @return false and true
	 */
	public boolean deleteOrderItemByOrderId(Long order_id) throws Exception;
	
	/**
	 * 通过订单项id删除订单项
	 * @param orderItem_id 订单项id
	 * @return false and true
	 */
	public boolean deletOrderItemById(Long orderItem_id) throws Exception;

}
