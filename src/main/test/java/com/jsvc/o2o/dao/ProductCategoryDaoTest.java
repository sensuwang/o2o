package com.jsvc.o2o.dao;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.entity.ProductCategory;
import com.jsvc.o2o.entity.ShopCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

//按照方法名字执行测试（对方法名排序）
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void queryProductCategory() {
        long id = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(20L);
        System.out.println("数量为：" + productCategoryList.size());
    }

    @Test
    public void batchInsertProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("sensu测试1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(15L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("sensu测试2");
        productCategory1.setPriority(1);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(15L);
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory1);
        int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectNum);
    }

    @Test
    public void deleteProductCategory(){
        long shopId = 15L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(shopId);
        for(ProductCategory productCategory : productCategoryList){
            if("sensu测试1".equals(productCategory.getProductCategoryName())){
                int effectNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(), shopId);
                assertEquals(effectNum, 1);
            }
        }
    }
}