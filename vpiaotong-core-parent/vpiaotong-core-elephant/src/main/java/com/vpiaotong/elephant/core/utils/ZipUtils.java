package com.vpiaotong.elephant.core.utils;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * <p>zip压缩</p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年9月8日 上午9:41:43
 */
public class ZipUtils {
	public static String zip(File file,String filePath,String pwd){
		
		ZipFile zipFile;
		ZipParameters parameters = getZipParameters(pwd);
		
		try {
//			/filePath=filePath+".zip";
			zipFile = new ZipFile(filePath);
			zipFile.addFile(file, parameters);
			return filePath;
		} catch (ZipException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	 private static ZipParameters getZipParameters(String pwd) {
		 ZipParameters parameters=new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
			
			if(StringUtils.isNotEmpty(pwd)){
				parameters.setEncryptFiles(true);
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				parameters.setPassword(pwd);
			}
		return parameters;
	}

	/** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @param pwd :压缩密码
     * @return 
	 * @throws ZipException 
     */  
    public static String fileToZip(String sourceFilePath,String zipFilePath,String fileName,String pwd) throws ZipException{  
        String flag = "";  
        File sourceFile = new File(sourceFilePath);  
        ZipParameters parameters = getZipParameters(pwd);
		
		ZipFile zipFile;
          
        if(sourceFile.exists() == false){  
        	
        }else{  
                File zipFile1 = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile1.exists()){  
                	
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();  
                    if(null == sourceFiles || sourceFiles.length<1){  
                    	
                    }else{  
                        
                        for(int i=0;i<sourceFiles.length;i++){  
                            zipFile = new ZipFile(zipFile1);
                			zipFile.addFile(sourceFiles[i], parameters);
                        }  
                        flag = zipFile1.getPath();  
                    }  
                }  
        }  
        return flag;  
    }  
}
