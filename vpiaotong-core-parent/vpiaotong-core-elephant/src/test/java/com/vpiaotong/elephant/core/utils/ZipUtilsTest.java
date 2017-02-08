package com.vpiaotong.elephant.core.utils;

import static com.vpiaotong.elephant.core.utils.ZipUtils.zip;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class ZipUtilsTest {

	@Test
	public void testZip() {
		String filePath="C:/Users/腾金玉/Desktop/服务商管理系统V0.2＋0912.zip";
		filePath=zip(new File(filePath), filePath, "123@#");
		if(StringUtils.isNotEmpty(filePath)){
			System.out.println("文件zip压缩成功！路径为"+filePath);
		}else{
			System.out.println("文件zip压缩失败！");
		}
	}

}
