package com.jsvc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ShopAdminController
 * @Author sensu
 * @Date 2019/9/5 18:42
 **/

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value = "/shopoperate")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanage", method = RequestMethod.GET)
    public String productCategoryManagement(){
        return "shop/productcategorymanage";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }
}
