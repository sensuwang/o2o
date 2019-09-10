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
}
