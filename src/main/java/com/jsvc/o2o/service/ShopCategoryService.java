package com.jsvc.o2o.service;

import com.jsvc.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    /**
     * 根据条件返回满足条件的信息
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
