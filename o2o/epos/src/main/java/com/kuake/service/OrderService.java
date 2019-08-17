package com.kuake.service;

import java.util.List;

import com.kuake.pojo.Order;

public interface OrderService {

	List<Order> getAll()throws Exception;

}
