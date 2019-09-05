package com.jsvc.o2o.service;

import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgImpugStream, String fileName);
}
