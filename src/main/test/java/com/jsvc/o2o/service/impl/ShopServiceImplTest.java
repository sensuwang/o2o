package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Area;
import com.jsvc.o2o.entity.PersonInfo;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.entity.ShopCategory;
import com.jsvc.o2o.enums.ShopStateEnum;
import com.jsvc.o2o.service.AreaService;
import com.jsvc.o2o.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceImplTest extends BaseTest {

    @Autowired
    public ShopService shopService;
    @Test
    public void addShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        Area area = new Area();
        personInfo.setUserId(11L);
        shopCategory.setShopCategoryId(22L);
        area.setAreaId(3L);
        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopName("测试的有图片上传的店铺");
        shop.setCreateTime(new Date());
        shop.setAdvice("审核中");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        File shopImg = new File("D:\\projectdev\\2.png");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, is, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}