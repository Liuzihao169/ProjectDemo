package com.kuake.vo;

import java.util.ArrayList;
import java.util.List;

import com.kuake.pojo.Order;
import com.kuake.pojo.OrderItem;

/**
 * @author Hao
 * @date: 2019年3月10日 下午1:30:36 
 * @Description: 这是一个包装类用来接收订单参数，用于封装。
 */
public class OrderInfo extends Order {
	
	private List<OrderItem>list=new ArrayList<>();

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	

}
