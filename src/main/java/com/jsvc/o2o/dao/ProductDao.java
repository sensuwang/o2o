package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Author sensu
 * @Date 2019/9/10 14:07
 **/
public interface ProductDao {

    /**
     * 查询商品列表并分页，可输入的条件有（商品名，商品状态，店铺id， 商品类别）
     *
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") long rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询对应商品总数（返回满足条件的商品总数）
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 插入商品
     *
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

    /**
     * 将商品类别ID置为空（删除商品类别之前）
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

}
