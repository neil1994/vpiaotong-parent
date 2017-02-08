package com.vpiaotong.elephant.core.bean;

import java.io.Serializable;

/**
 * 
 * <p>短信协议对象</p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年6月14日 下午4:28:15
 */
public class DxDto implements Serializable{

	private static final long serialVersionUID = 5193989002437302966L;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 操作
	 */
	private String operation;
	
	/**
	 * 失效时间，默认失效时间为10分钟
	 */
	private int expiryDate=10;
	
	/**
	 * 校验码长度,默认长度为4
	 */
	private int autoCodeLength=4;
	
	/**
	 * 校验码
	 */
	private String autoCode;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getAutoCodeLength() {
		return autoCodeLength;
	}

	public void setAutoCodeLength(int autoCodeLength) {
		this.autoCodeLength = autoCodeLength;
	}

	public String getAutoCode() {
		return autoCode;
	}

	public void setAutoCode(String autoCode) {
		this.autoCode = autoCode;
	}
	
}
