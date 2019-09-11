package com.jsvc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "index", method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }
}
