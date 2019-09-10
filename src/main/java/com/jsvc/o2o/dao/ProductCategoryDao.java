package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ProductCategoryDao
 * @Author sensu
 * @Date 2019/9/9 22:18
 **/
public interface ProductCategoryDao {
    /**
     * 通过shopId查询商铺商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategory(long shopId);

    /**
     * 新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除指定商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
