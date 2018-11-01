package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    /**
     * 获取所有商品的信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 保存商品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    /*@SelectKey(keyProperty = "id", keyColumn = "id", statement = {"select SYS_GUID from dual"}, before = true, resultType = String.class)*/
    void save(Product product);
}
