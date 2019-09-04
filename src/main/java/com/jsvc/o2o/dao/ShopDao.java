package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.Shop;

/**
 * @ClassName ShopDao
 * @Author sensu
 * @Date 2019/8/23 19:48
 **/
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
