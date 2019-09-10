package com.jsvc.o2o.exceptions;

/**
 * @ClassName ShopOperationException
 * @Author sensu
 * @Date 2019/9/4 23:48
 **/
public class ProductCategoryOperationException extends RuntimeException {
    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
