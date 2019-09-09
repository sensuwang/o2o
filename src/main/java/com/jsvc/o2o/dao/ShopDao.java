package com.jsvc.o2o.dao;

import com.beust.jcommander.Parameter;
import com.jsvc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ShopDao
 * @Author sensu
 * @Date 2019/8/23 19:48
 **/
public interface ShopDao {
    /**
     * 分页查询店铺  可输入的条件有 店铺名（模糊）  店铺状态  店铺类别  区域id  owner
     * @param shopCondition
     * @param rowIndex 从第几行开始取数据
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex")int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 返回条件查询的店铺总数
     * @param shopCondition 条件查询
     * @return
     */
    int queryShopCount(@Param("shopCondition")Shop shopCondition);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    /**
     * 通过id查询shop
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);
}
