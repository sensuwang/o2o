package com.jsvc.o2o.service;

import com.jsvc.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName HeadLineService
 * @Author sensu
 * @Date 2019/9/11 22:49
 **/
public interface HeadLineService {
    /**
     * 根据传入的条件返回指定的头条列表
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
