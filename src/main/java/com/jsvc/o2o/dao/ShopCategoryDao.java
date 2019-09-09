package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ShopCategoryDao
 * @Author sensu
 * @Date 2019/9/7 10:15
 **/
public interface ShopCategoryDao {

    //@Param 可以设置参数的别名方便在xml中取值
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
