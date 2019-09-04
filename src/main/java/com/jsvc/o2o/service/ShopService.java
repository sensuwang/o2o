package com.jsvc.o2o.service;

import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
