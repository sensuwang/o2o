package com.jsvc.o2o.util;

import ch.qos.logback.core.util.FileUtil;
import com.jsvc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName ImageUtil
 * @Author sensu
 * @Date 2019/9/4 16:16
 **/
public class ImageUtil {
    private  static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static  final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static  final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转换为File
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartsFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 处理缩略图并返回路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 处理图片详情，并返回新生成图片的相对值路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        //获取不重复的随即名
        String realFileName = getRandomFileName();
        //获取文件的拓展名如jpg,png等
        String extension = getFileExtension(thumbnail.getImageName());
        //如果文件不存在，则自动创建
        makeDirPath(targetAddr);
        //文件存储的相对路径（带文件名）
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        //调用Thumbnails生成有水印的图片
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640).outputQuality(0.5f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/sensu/xxx.jpg
     * 那么 home work sensu这三个文件夹都得自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+5位随机数
     * @return
     */
    public static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;// 大于10000 小于 99999
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if(fileOrPath.exists()){
            if(fileOrPath.isDirectory()){
                File[] files = fileOrPath.listFiles();
                for(int i=0; i<files.length; i++){
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
