package com.jsvc.o2o.web.superadmin;

import com.jsvc.o2o.entity.Area;
import com.jsvc.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AreaController
 * @Author sensu
 * @Date 2019/8/22 20:50
 **/
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    //自动将返回转换为json
    @ResponseBody
    private Map<String, Object>  listArea(){
        logger.info("=====start=====");
        long startTime = System.currentTimeMillis();
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> list = new ArrayList<>();
        try{
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e){
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.error("test error!");
        long endTime = System.currentTimeMillis();
        //会将第二个参数的值传入大括号中
        logger.debug("constTim:[{}ms]", endTime-startTime);
        logger.info("=====end=====");
        return modelMap;
    }



}
