package com.jsvc.o2o.service;

import java.util.List;

import com.jsvc.o2o.dto.ImageHolder;
import com.jsvc.o2o.dto.ProductExecution;
import com.jsvc.o2o.entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ProductService {
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 通过商品id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 商品信息上传以及图片处理
     *
     * @param product        商品信息
     * @param thumbnail      缩略图信息
     * @param productImgList 详细图片信息
     * @return
     * @throws RuntimeException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> productImgList) throws RuntimeException;

    /**
     * 修改商品信息的方法
     * @param product
     * @param thumbnail
     * @param productImgs
     * @return
     * @throws RuntimeException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
                                   List<ImageHolder> productImgs) throws RuntimeException;
}
