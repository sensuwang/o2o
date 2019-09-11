package com.jsvc.o2o.dao;

import com.jsvc.o2o.BaseTest;
import com.jsvc.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void queryHeadLine() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(4, headLineList.size());
    }

    @Test
    public void queryHeadLineById() {
    }

    @Test
    public void queryHeadLineByIds() {
    }

    @Test
    public void insertHeadLine() {
    }

    @Test
    public void updateHeadLine() {
    }

    @Test
    public void deleteHeadLine() {
    }

    @Test
    public void batchDeleteHeadLine() {
    }
}