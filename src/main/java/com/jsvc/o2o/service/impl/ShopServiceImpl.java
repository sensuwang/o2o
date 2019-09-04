package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.enums.ShopStateEnum;
import com.jsvc.o2o.service.ShopService;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * @ClassName ShopServiceImpl
 * @Author sensu
 * @Date 2019/9/4 21:40
 **/
public class ShopServiceImpl implements ShopService {
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        return null;
    }
}
