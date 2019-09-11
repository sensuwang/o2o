package com.jsvc.o2o.service.impl;

import com.jsvc.o2o.dao.HeadLineDao;
import com.jsvc.o2o.entity.HeadLine;
import com.jsvc.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName HeadLineServiceImpl
 * @Author sensu
 * @Date 2019/9/11 22:50
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
