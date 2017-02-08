package com.vpiaotong.elephant.core.utils.file.image;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.vpiaotong.elephant.core.utils.exception.UploadException;
import com.vpiaotong.elephant.core.utils.file.FileTypeUtil.FileTypeEnum;

/**
 * 上传的Image文件对象
 * 
 * @author Acmen
 */
public class ImageFile {

    /** 文件原名称 */
    private String fileName = null;

    /** 文件名称后缀 */
    private String fileSuffix = null;

    /** 文件数据 */
    private byte[] fileData = null;

    /** 文件是否是excel */
    private boolean isImage = false;

    /** 文件版本类型 */
    private FileTypeEnum fileType = null;

    /**
     * 私有化构造函数，防止没构建
     */
    @SuppressWarnings("unused")
    private ImageFile() {
        throw new IllegalAccessError();
    }

    /**
     * 初始化ExcelFile对象
     * 
     * @param fileName
     *            文件原名
     * @param stream
     *            输入流
     * @throws UploadException
     *             上传异常
     * @throws IOException
     */
    public ImageFile(String fileName, InputStream stream) throws IOException {
        try {
            this.fileName = fileName;
            this.fileData = IOUtils.toByteArray(stream);
            this.fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            this.fileType = ImageCheckUtil.getImageType(this.fileSuffix, fileData);
            if (null != this.fileType) {
                this.isImage = true;
            }
        }
        finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * 文件原名称
     * 
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名后缀
     * 
     * @return String
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * 文件上传数据
     * 
     * @return byte[]
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * 是否是图片
     * 
     * @return true||false
     */
    public boolean isImage() {
        return isImage;
    }

    /**
     * 图片类型
     * 
     * @return FileTypeEnum||null
     */
    public FileTypeEnum getFileType() {
        return fileType;
    }
}
