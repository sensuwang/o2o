package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.dao.ShopCategoryDao;
import com.jsvc.o2o.entity.ShopCategory;
import com.jsvc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShopCategoryImpl
 * @Author sensu
 * @Date 2019/9/7 10:53
 **/
@Service
public class ShopCategoryImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
