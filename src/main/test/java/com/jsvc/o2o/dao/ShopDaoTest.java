package com.jsvc.o2o.dao;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.entity.Area;
import com.jsvc.o2o.entity.PersonInfo;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {

    @Autowired
    ShopDao shopDao;
    @Test
    public void insertShop() {
        PersonInfo personInfo = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        Area area = new Area();
        personInfo.setUserId(11L);
        shopCategory.setShopCategoryId(22L);
        area.setAreaId(3L);
        Shop shop = new Shop();
        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("王白test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void updateShop() {
        Shop shop = new Shop();
        shop.setShopId(30L);
        shop.setShopName("修改测试");
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void queryByShopId(){
        Shop shop = shopDao.queryByShopId(15L);
        System.out.println(shop.getArea().getAreaName());

    }
}