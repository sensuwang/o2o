package com.jsvc.o2o.service;

import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 根据id查询店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 修改店铺信息，包括对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName);

    /**
     * 添加店铺信息，包括对图片的处理
     * @param shop
     * @param shopImgImpugStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgImpugStream, String fileName);


}
