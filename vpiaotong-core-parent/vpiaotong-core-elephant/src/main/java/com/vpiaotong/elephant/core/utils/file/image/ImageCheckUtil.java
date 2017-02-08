package com.vpiaotong.elephant.core.utils.file.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.vpiaotong.elephant.core.utils.file.FileTypeUtil;
import com.vpiaotong.elephant.core.utils.file.FileTypeUtil.FileTypeEnum;

import net.sf.image4j.codec.ico.ICODecoder;

/**
 * 图片检查工具类
 * 
 * @author Acmen
 */
public final class ImageCheckUtil {

    /** 图片类型Map */
    private final static List<FileTypeEnum> IMAGE_TYPE_MAP = new LinkedList<FileTypeEnum>();

    /** JDK支持的图片类型 */
    private final static List<String> JDK_SUPPORT_IMAGE_TYPES = Arrays.asList(ImageIO.getReaderFileSuffixes());

    static {
        // 初始化文件类型信息
        getAllImageType();
    }

    /**
     * 私有化构造函数，防止没构建
     */
    private ImageCheckUtil() {
        throw new IllegalAccessError();
    }

    /**
     * 图片头信息
     */
    private static void getAllImageType() {
        IMAGE_TYPE_MAP.add(FileTypeEnum.JPEG);
        IMAGE_TYPE_MAP.add(FileTypeEnum.PNG);
        IMAGE_TYPE_MAP.add(FileTypeEnum.GIF);
        IMAGE_TYPE_MAP.add(FileTypeEnum.BMP);
        IMAGE_TYPE_MAP.add(FileTypeEnum.ICO);
        // IMAGE_TYPE_MAP.add(FileTypeEnum.TIFF);
    }

    /**
     * 获取图片类型
     * 
     * @param fileSuffix
     *            文件后缀名
     * @param fileByte
     *            上传的文件数据
     * @return 图片类型||null
     * @throws IOException
     */
    public static FileTypeEnum getImageType(String fileSuffix, byte[] fileByte) throws IOException {
        FileTypeEnum fileType = null;
        InputStream byteStream = new ByteArrayInputStream(fileByte);
        try {
            BufferedImage bufreader = null;
            // 根据文件后缀名判断ImageIO是否能处理
            boolean isJdkSupportImageTypes = isJdkSupportImageTypes(fileSuffix);
            if (isJdkSupportImageTypes) {
                bufreader = ImageIO.read(byteStream);
            }
            else {
                List<BufferedImage> bufferedList = ICODecoder.read(byteStream);
                if (!bufferedList.isEmpty()) {
                    bufreader = bufferedList.get(0);
                }
            }
            if (null != bufreader) {
                int width = bufreader.getWidth();
                int height = bufreader.getHeight();
                if (width != 0 && height != 0) {
                    // 获取图片类型
                    fileType = checkImageData(fileByte);
                }
            }
        }
        finally {
            byteStream.close();
        }
        return fileType;
    }

    /**
     * 获取图片类型
     * 
     * @param fileByte
     *            图片数据
     * @return FileTypeEnum
     */
    private static FileTypeEnum checkImageData(byte[] fileByte) {
        FileTypeEnum fileType = FileTypeUtil.getFileTypeByStream(fileByte);
        if (null != fileType) {
            if (!IMAGE_TYPE_MAP.contains(fileType)) {
                fileType = null;
            }
        }
        return fileType;
    }

    /**
     * 根据文件后缀名判断是否为JDk支持的图片类型
     * 
     * @param fileSuffix
     *            文件后缀名
     * @return true||false
     */
    private static boolean isJdkSupportImageTypes(String fileSuffix) {
        if (JDK_SUPPORT_IMAGE_TYPES.contains(fileSuffix.toLowerCase())) {
            return true;
        }
        return false;
    }
}
