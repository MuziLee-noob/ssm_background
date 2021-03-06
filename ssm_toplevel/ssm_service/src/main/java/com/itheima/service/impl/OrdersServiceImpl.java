package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao od;

    /**
     * 查询所有订单
     * @param startPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Orders> findAll(int startPage, int pageSize) {
        Page<Orders> page = PageHelper.startPage(startPage, pageSize);

        return od.findAll();
    }

    /**
     * 通过id查询订单
     * @param id
     * @return
     */
    @Override
    public Orders findById(String id) {
        return od.findById(id);
    }
}
