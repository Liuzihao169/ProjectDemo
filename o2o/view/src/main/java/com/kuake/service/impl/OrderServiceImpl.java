package com.kuake.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuake.dao.CartMapper;
import com.kuake.dao.GreensMapper;
import com.kuake.dao.OrderItemMapper;
import com.kuake.dao.OrderMapper;
import com.kuake.pojo.Order;
import com.kuake.pojo.OrderItem;
import com.kuake.service.OrderService;
import com.kuake.utils.IDUtils;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private GreensMapper greensMapper;

	@Override
	public void saveOrder(Order orderInfo) throws Exception {
		// 封装order的完整性
		Long orderCode = IDUtils.genItemId();
		orderInfo.setOrder_code(orderCode.toString());
		orderInfo.setOrder_time(new Date());
		// 1 代表有效 0 代表失效
		orderInfo.setOrder_status(1);
		// 插入订单
		orderMapper.saveOrder(orderInfo);
		// 取出自增加的主键order_id
		Long id = orderInfo.getOrder_id();
		// 获得list插入订单项
		List<OrderItem> list = orderInfo.getOrderItems();
		for (OrderItem orderItem : list) {
			// 插入id,保存订单项目
			Order order = new Order();
			order.setOrder_id(id);
			orderItem.setOrder(order);
			// 插入订单项目
			orderItemMapper.saveOrderItem(orderItem);
			// 更改greens的库存和销量
			greensMapper.updateGreensNum(orderItem.getOrderitem_count(), orderItem.getGreens().getGreens_id());
			//执行清空购物车。
			cartMapper.deleteCartByUid(orderInfo.getUser().getUser_id());
		}
	}

	@Override
	public List<Order> findOrdersByUid(Long id) throws Exception {
		List<Order> list = orderMapper.findOrderListByUid(id);
		return list;
	}
}
