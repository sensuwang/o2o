package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.dto.ImageHolder;
import com.jsvc.o2o.dto.ProductExecution;
import com.jsvc.o2o.entity.Product;
import com.jsvc.o2o.entity.ProductCategory;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.enums.ProductStateEnum;
import com.jsvc.o2o.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class productServiceImplTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void getProductList() {
    }

    @Test
    public void getProductById() {
    }

    @Test
    public void addProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(46L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(26L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File shopImg = new File("D:\\projectdev\\2.png");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder thumbnail = new ImageHolder(shopImg.getName(), is);

        File productImg = new File("D:\\projectdev\\2.png");
        InputStream is1 = new FileInputStream(shopImg);
        File productImg2 = new File("D:\\projectdev\\2.png");
        InputStream is2 = new FileInputStream(shopImg);
        List<ImageHolder> imageHolderList = new ArrayList<>();
        imageHolderList.add(new ImageHolder(productImg.getName(), is1));
        imageHolderList.add(new ImageHolder(productImg.getName(), is2));
        ProductExecution pe = productService.addProduct(product, thumbnail, imageHolderList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());

    }

    @Test
    public void modifyProduct() {
    }
}