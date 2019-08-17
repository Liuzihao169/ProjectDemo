package com.kuake.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	//订单ID
	private Long order_id;
	//订单编号
	private String order_code;
	//订单电话
	private String order_phone;
	//订单用户真实姓名
	private String order_realname;
	//点单地址
	private String order_address;
	//订单总计金额
	private Double order_total;
	//下单时间
	private Date order_time;
	//订单状态
	private Integer order_status;
	
	//订单项
	private List<OrderItem> orderItems;
	
	//下单用户
	private User user;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getOrder_phone() {
		return order_phone;
	}

	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}

	public String getOrder_realname() {
		return order_realname;
	}

	public void setOrder_realname(String order_realname) {
		this.order_realname = order_realname;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public Double getOrder_total() {
		return order_total;
	}

	public void setOrder_total(Double order_total) {
		this.order_total = order_total;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
