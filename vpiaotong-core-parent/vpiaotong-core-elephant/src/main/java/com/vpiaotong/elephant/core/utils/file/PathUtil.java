package com.vpiaotong.elephant.core.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 类路径工具类
 * 
 * @author Acmen
 */
public final class PathUtil {

    /**
     * 私有化构造函数，防止没构建
     */
    private PathUtil() {
        throw new IllegalAccessError();
    }

    /**
     * 检查系统类型
     * 
     * @return windows:true||linux:false
     */
    public static boolean getSystemType() {
        boolean isWindows = false;
        String system = System.getProperties().getProperty("os.name").toUpperCase();
        if (system.indexOf("WINDOWS") != -1) {
            isWindows = true;
        }
        return isWindows;
    }

    /**
     * 获取文件真实路径
     * 
     * @param filePath
     *            路径
     * @return 真实路径
     */
    public static String getRealFilePath(String filePath) {
        boolean isWindows = getSystemType();
        if (isWindows) {
            // WINDOWS
            filePath = filePath.replaceAll("[\\\\/]", File.separator + File.separator);
        }
        else {
            // LINUX
            filePath = filePath.replaceAll("[\\\\/]", File.separator);
        }
        return filePath;
    }

    /**
     * 根据系统类型获得文件真实全路径(如果fileName带后缀，filePrex参数传入null)
     * 
     * @param outputFilePath
     *            文件所在路径
     * @param fileName
     *            文件名称
     * @param filePrex
     *            文件后缀
     * @return 文件全路径
     */
    public static String getRealFilePath(String outputFilePath, String fileName, String filePrex) {
        String realSavePath = getRealFilePath(outputFilePath);
        if (!realSavePath.endsWith(File.separator)) {
            realSavePath += File.separator;
        }
        realSavePath += fileName;
        if (null != filePrex && !filePrex.isEmpty()) {
            realSavePath += "." + filePrex;
        }
        return realSavePath;
    }

    /**
     * 取得当前类所在的文件
     * 
     * @param clazz
     * @return
     */
    public static File getClassFile(Class<?> clazz) {
        URL path = clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".class");
        if (path == null) {
            String name = clazz.getName().replaceAll("[.]", "/");
            path = clazz.getResource("/" + name + ".class");
        }
        return new File(path.getFile());
    }

    /**
     * 同getClassFile 解决中文编码问题
     * 
     * @param clazz
     * @return
     */
    public static String getClassFilePath(Class<?> clazz) {
        try {
            return java.net.URLDecoder.decode(getClassFile(clazz).getAbsolutePath(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 取得当前类所在的ClassPath目录
     * 
     * @param clazz
     * @return
     */
    public static File getClassPathFile(Class<?> clazz) {
        File file = getClassFile(clazz);
        for (int i = 0, count = clazz.getName().split("[.]").length; i < count; i++)
            file = file.getParentFile();
        if (file.getName().toUpperCase().endsWith(".JAR!")) {
            file = file.getParentFile();
        }
        return file;
    }

    /**
     * 同getClassPathFile 解决中文编码问题
     * 
     * @param clazz
     * @return
     */
    public static String getClassPath(Class<?> clazz) {
        try {
            return java.net.URLDecoder.decode(getClassPathFile(clazz).getAbsolutePath(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取一个Class的绝对路径
     * 
     * @param clazz
     *            Class对象
     * @return Class的绝对路径
     */
    public static String getPathByClass(Class<?> clazz) {
        String path = null;
        try {
            URI uri = clazz.getResource("").toURI();
            File file = new File(uri);
            path = file.getCanonicalPath();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 获取当前项目的根路径
     * 
     * @return 当前项目的Web根路径
     */
    public static String getWebRootPath() {
        String pathByClass = getClassFilePath(PathUtil.class);
        String rootDirectory = "WEB-INF";
        int webRootIndex = pathByClass.indexOf(rootDirectory);
        String classRootPath = pathByClass.substring(0, webRootIndex);
        return classRootPath;
    }

    /**
     * 获取当前项目的WEB-INF路径
     * 
     * @return 当前项目的WEB-INF根路径
     */
    public static String getWebInfPath() {
        String pathByClass = getClassFilePath(PathUtil.class);
        String rootDirectory = "classes";
        int webRootIndex = pathByClass.indexOf(rootDirectory);
        String classRootPath = pathByClass.substring(0, webRootIndex);
        return classRootPath;
    }

    /**
     * 获取当前项目的classes根路径
     * 
     * @return 当前项目的classes根路径
     */
    public static String getClassRootPath() {
        String pathByClass = getClassFilePath(PathUtil.class);
        String classesDirectory = "classes";
        int classesRootIndex = pathByClass.indexOf(classesDirectory);
        String classRootPath = pathByClass.substring(0, classesRootIndex + (classesDirectory.length() + 1));
        return classRootPath;
    }
}