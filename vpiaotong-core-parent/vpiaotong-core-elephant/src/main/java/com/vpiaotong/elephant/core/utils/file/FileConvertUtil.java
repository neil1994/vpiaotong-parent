package com.vpiaotong.elephant.core.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件转换工具类
 * 
 * @author Acmen
 */
public final class FileConvertUtil {

    /**
     * 私有化构造函数，防止没构建
     */
    private FileConvertUtil() {
        throw new IllegalAccessError();
    }

    /**
     * 文件转byte[]
     * 
     * @param inputFileDir
     *            文件所在硬盘路径
     * @return byte[]||null
     */
    public static byte[] fileToBytes(String inputFileDir) {
        byte[] tagInfo = null;
        if (null != inputFileDir && !inputFileDir.isEmpty()) {
            File file = new File(PathUtil.getRealFilePath(inputFileDir));
            tagInfo = fileToBytes(file);
        }
        return tagInfo;
    }

    /**
     * 文件转byte[]
     * 
     * @param inputFileDir
     *            文件所在硬盘路径
     * @return byte[]||null
     */
    public static byte[] fileToBytes(File srcFile) {
        byte[] tagInfo = null;
        try {
            // file.exists():检查文件是否存
            // file.isFile():检查文件是否是一个普通的文件
            if (srcFile.exists() && srcFile.isFile()) {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(srcFile), 1024 * 1024);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // 创建1M字节缓冲区
                BufferedOutputStream bos = new BufferedOutputStream(out, 1024 * 1024);
                int i = 0;
                while ((i = inputStream.read()) != -1) {
                    bos.write(i);
                }
                bos.close();
                out.close();
                inputStream.close();
                tagInfo = out.toByteArray();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return tagInfo;
    }

    /**
     * 保存文件到硬盘上(byte[]转文件)
     * 
     * @param byteArray
     *            byte数据
     * @param outputFileName
     *            输出文件名(如果outputFileName带后缀，outputFilePrex参数请传入null)
     * @param outputFilePrex
     *            输出文件后缀(如果outputFileName带后缀，outputFilePrex参数请传入null)
     * @param outputFilePath
     *            输出文件路径
     * @return boolean
     */
    public static boolean byteToFile(byte[] byteArray, String outputFileName, String outputFilePrex,
            String outputFilePath) {
        boolean byteToFileResult = false;
        // 判断byte数据、文件名称、输出路径不能为空
        if (null == byteArray || null == outputFilePath || outputFilePath.isEmpty() || null == outputFileName
                || outputFileName.isEmpty()) {
            return byteToFileResult;
        }
        BufferedOutputStream stream = null;
        File outputPathFile = new File(outputFilePath);
        // outputFile.exists():目录是否存在
        // outputFile.isDirectory():此路径是一个目录该方法返回true,否则该方法返回false.
        if (!outputPathFile.exists() && !outputPathFile.isDirectory()) {
            // 判断创建目录是否成功
            if (!outputPathFile.mkdirs()) {
                // 创建目录失败直接返回失败结果
                return byteToFileResult;
            }
        }
        try {
            String realFullSavePath = PathUtil.getRealFilePath(outputFilePath, outputFileName, outputFilePrex);
            File outputFile = new File(realFullSavePath);
            stream = new BufferedOutputStream(new FileOutputStream(outputFile), 1024 * 1024);
            stream.write(byteArray);
            // 迫使所有缓冲的输出数据被写出到底层输出流中(主动调用flush()方法,防止数据无法写到输出流中)
            stream.flush();
            byteToFileResult = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteToFileResult;
    }
}
