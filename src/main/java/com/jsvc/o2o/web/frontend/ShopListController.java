package com.jsvc.o2o.web.frontend;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jsvc.o2o.dto.ShopExecution;
import com.jsvc.o2o.entity.Area;
import com.jsvc.o2o.entity.Shop;
import com.jsvc.o2o.entity.ShopCategory;
import com.jsvc.o2o.service.AreaService;
import com.jsvc.o2o.service.ShopCategoryService;
import com.jsvc.o2o.service.ShopService;
import com.jsvc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShopListController
 * @Author sensu
 * @Date 2019/9/16 22:27
 **/
@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    @GetMapping("listshopspageinfo")
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1) {
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        //获取一页需要显示的数据条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //非空判断
        if ((pageIndex > -1) && (pageSize > -1)) {
            //试着获取一级类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            //试着获取特定二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            //试着获取区域id
            long areaId = HttpServletRequestUtil.getLong(request, "areaId");
            //试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            //获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            //格局查询条件和分页信息获取店铺列表，并返回总数
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId,
                                             long shopCategoryId, long areaId, String shopName) {
        Shop shopCondition = new Shop();
        if (parentId != -1L) {
            ShopCategory childShopCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childShopCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childShopCategory);
        }
        if (shopCategoryId != -1L) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1L) {
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }

        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
