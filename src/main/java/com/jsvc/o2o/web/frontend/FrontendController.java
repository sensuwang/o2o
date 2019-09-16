package com.jsvc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName FrontendController
 * @Author sensu
 * @Date 2019/9/11 23:17
 **/
@Controller
@RequestMapping("/frontend")
public class FrontendController {
    /**
     * 主页
     * @return
     */
    @GetMapping(value = "index")
    private String index(){
        return "frontend/index";
    }

    /**
     * 店铺列表
     * @return
     */
    @GetMapping(value = "shoplist")
    private String shopShopList(){
        return "frontend/shoplist";
    }

    /**
     * 店铺详情页
     * @return
     */
    @GetMapping(value = "shopDetail")
    private String showShopDetail(){
        return "frontend/shopdetail";
    }

    /**
     * 商品详情页
     * @return
     */
    @GetMapping(value = "productDetail")
    private String shopProductDetail(){
        return "frontend/productdetail";
    }
}
