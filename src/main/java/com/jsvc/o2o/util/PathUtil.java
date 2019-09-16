package com.jsvc.o2o.util;

/**
 * @ClassName PathUtil
 * @Author sensu
 * @Date 2019/9/4 20:03
 **/
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image";
        }else{
            basePath = "/home/sensu/image";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";

        return imagePath.replace("/", seperator);
    }
}
