package com.vpiaotong.elephant.core.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * 描述信息：日期格式转换
 *
 * @author 腾金玉
 * @version 1.0 Created on 2016年8月10日 下午1:13:31
 */
public class DateToString {
	
	private final static Logger logger = LoggerFactory.getLogger(DateToString.class);
	/**
	 * 
	 * <p>功能实现描述</p>
	 * 
	 * @param date
	 * @return String
	 * @author: 腾金玉
	 * @date: Created on 2016年8月15日 上午10:19:36
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String str = format.format(date);
		return str;
	}
	
	/**
	 * 
	 * <p>功能实现描述</p>
	 * 
	 * @param date
	 * @return String
	 * @author: 腾金玉
	 * @date: Created on 2016年8月15日 上午10:19:36
	 */
	public static String ToDateStr(String String) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(String);
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			logger.info("日期转换异常");
			e.printStackTrace();
			return null;
		}
	}
	public static String ToDateStrSFM(String String) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse(String);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (ParseException e) {
			logger.info("日期转换异常");
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		String toDateStr = DateToString.ToDateStr("2015-12-03 13:56:56");
		System.out.println(toDateStr);
	}
}
