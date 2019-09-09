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
import java.util.List;

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

    @Test
    public void modifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(34L);
        shop.setShopName("店铺修改测试");
        InputStream is = new FileInputStream(new File("D:\\projectdev\\DSC_0163.JPG"));
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "DSC_0163.JPG");
        System.out.println(shopExecution.getShop().getShopImg());

    }

    @Test
    public void getShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(8L);
        shopCondition.setOwner(owner);
        ShopExecution se = shopService.getShopList(shopCondition, 4, 5);
        System.out.println("店铺列表的大小： " + se.getShopList().size());
        System.out.println("店铺的总数为： " + se.getCount());


    }
}