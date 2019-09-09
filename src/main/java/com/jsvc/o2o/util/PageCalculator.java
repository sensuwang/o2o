package com.jsvc.o2o.util;

/**
 * @ClassName PageCalculator
 * @Author sensu
 * @Date 2019/9/9 20:40
 **/
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
