package com.jsvc.o2o.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.jsvc.o2o.dao.ProductDao;
import com.jsvc.o2o.dao.ProductImgDao;
import com.jsvc.o2o.dto.ImageHolder;
import com.jsvc.o2o.dto.ProductExecution;
import com.jsvc.o2o.entity.Product;
import com.jsvc.o2o.entity.ProductImg;
import com.jsvc.o2o.enums.ProductStateEnum;
import com.jsvc.o2o.service.ProductService;
import com.jsvc.o2o.util.ImageUtil;
import com.jsvc.o2o.util.PageCalculator;
import com.jsvc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName productServiceImpl
 * @Author sensu
 * @Date 2019/9/10 15:07
 **/
@Service
public class productServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //将页码转换为数据库的行数，然后调用dao取到指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        //基于同样条件返回该查询条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws RuntimeException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为上架的状态
            product.setEnableStatus(1);
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new RuntimeException("创建商品失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("创建商品失败:" + e.toString());
            }
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    //若缩略图参数有值，则处理缩略图
    //若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
    //将tb_product_img下面的该商品原先的商品详情记录全部清除
    //更新tb_product_img以及tb_product的信息
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
                                          List<ImageHolder> productImageHolderList) throws RuntimeException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置默认属性
            product.setLastEditTime(new Date());
            //若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            //如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if (productImageHolderList != null && productImageHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImageHolderList);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new RuntimeException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new RuntimeException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 上传商品详细图片
     *
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() >= 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new RuntimeException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("创建商品详情图片失败:" + e.toString());
            }
        }
    }

    /**
     * 缩略图的处理（上传并传回product中）
     *
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 删除详细图片信息
     * @param productId
     */
    private void deleteProductImgList(long productId) {
        //根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //kill原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里原来的图片信息
        productImgDao.deleteProductImgByProductId(productId);
    }
}
