package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.ProductImg;

import java.util.List;

/**
 * @ClassName ProductImgDao
 * @Author sensu
 * @Date 2019/9/10 14:08
 **/
public interface ProductImgDao {
    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 通过商品id查询对应所有的详细图片信息
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);
}
