package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ProductDao
 * @Author sensu
 * @Date 2019/9/10 14:07
 **/
public interface ProductDao {
    /**
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    int deleteProduct(@Param("productId") long productId,
                      @Param("shopId") long shopId);

}
