package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    /**
     * 获取所有商品的信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

}
