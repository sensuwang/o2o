package com.jsvc.o2o.service;

import com.jsvc.o2o.dto.ImageHolder;
import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 通过店铺id获取店铺信息
     * @param shopCondition 查询条件
     * @param pageIndex 页码数
     * @param pageSize 每页数据量
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    /**
     * 根据id查询店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 修改店铺信息，包括对图片的处理
     * @param shop
     * @param thumbnail
     * @return
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail);

    /**
     * 添加店铺信息，包括对图片的处理
     * @param shop
     * @param thumbnail
     * @return
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail);


}
