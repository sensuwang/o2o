package com.jsvc.o2o.dao;

import com.jsvc.o2o.entity.Area;

import java.util.List;

/**
 * @ClassName AreaDao
 * @Author sensu
 * @Date 2019/8/22 20:10
 **/
public interface AreaDao {
    /**
     * 列出区域列表
     *
     * @return areaList
     */
    List<Area> queryArea();
}
