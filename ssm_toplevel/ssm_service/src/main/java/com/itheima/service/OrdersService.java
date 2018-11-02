package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(int startPage, int pageSize);

    Orders findById(String id);
}
