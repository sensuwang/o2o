package com.jsvc.o2o.service;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AreaServiceTest extends BaseTest {

    //使用到areaService时， spring会注入实现类（service中有@service标签）
    @Autowired
    private AreaService areaService;
    @Test
    public void getAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("东苑", areaList.get(0).getAreaName());
    }
}