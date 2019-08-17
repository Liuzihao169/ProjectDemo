package com.kuake.pojo;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	//订单项ID
	private Long orderitem_id;
	//菜品数量
	private Integer orderitem_count;
	//小计
	private Double orderitem_total;
	
	//菜品
	private Greens greens;
	
	//订单
	private Order order;

	public Long getOrderitem_id() {
		return orderitem_id;
	}

	public void setOrderitem_id(Long orderitem_id) {
		this.orderitem_id = orderitem_id;
	}

	public Integer getOrderitem_count() {
		return orderitem_count;
	}

	public void setOrderitem_count(Integer orderitem_count) {
		this.orderitem_count = orderitem_count;
	}

	public Double getOrderitem_total() {
		return orderitem_total;
	}

	public void setOrderitem_total(Double orderitem_total) {
		this.orderitem_total = orderitem_total;
	}

	public Greens getGreens() {
		return greens;
	}

	public void setGreens(Greens greens) {
		this.greens = greens;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
