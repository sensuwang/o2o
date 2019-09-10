package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.Product;

/**
 * @ClassName ProductDao
 * @Author sensu
 * @Date 2019/9/10 14:07
 **/
public interface ProductDao {
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

}
