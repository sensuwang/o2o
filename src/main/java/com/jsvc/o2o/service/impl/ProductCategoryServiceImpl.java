package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.dao.ProductCategoryDao;
import com.jsvc.o2o.dao.ProductDao;
import com.jsvc.o2o.dto.ProductCategoryExecution;
import com.jsvc.o2o.entity.ProductCategory;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.enums.ProductCategoryStateEnum;
import com.jsvc.o2o.exceptions.ProductCategoryOperationException;
import com.jsvc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Request;

import java.util.List;

/**
 * @ClassName ProductCategoryServiceImpl
 * @Author sensu
 * @Date 2019/9/9 22:27
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategory(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new RuntimeException("店铺类别添加失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new RuntimeException("batchAddProductCategory error: " + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        //将此商品类别下的商品的类别id置空（有外键约束）
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if(effectedNum < 0){
                throw new RuntimeException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new RuntimeException("deleteProductCategory error:" + e.getMessage());
        }
        //删除商品类别
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(
                    productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺类别删除失败");
            } else {
                return new ProductCategoryExecution(
                        ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error: " + e.getMessage());
        }
    }
}
