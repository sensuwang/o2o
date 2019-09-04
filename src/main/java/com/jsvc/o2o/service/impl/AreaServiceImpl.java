package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.dao.AreaDao;
import com.jsvc.o2o.entity.Area;
import com.jsvc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AreaSerciceImpl
 * @Author sensu
 * @Date 2019/8/22 20:42
 **/
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
