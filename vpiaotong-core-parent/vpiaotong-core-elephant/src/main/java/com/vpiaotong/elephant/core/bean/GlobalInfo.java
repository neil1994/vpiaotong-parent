package com.vpiaotong.elephant.core.bean;

import java.io.Serializable;

/**
 * 
 * <p>外层报文协议对象</p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年6月14日 下午4:31:49
 */
public class GlobalInfo implements Serializable {
	private static final long serialVersionUID = 345139520322683756L;
	
	/**
	 * 服务商id
	 */
	private String fwsid;
	/**
	 * 服务id
	 */
	private String serviceid;
	/**
	 * 请求方式-通过api对接方式
	 */
	private String appid;
	/**
	 * 接口编码
	 */
	private String interfacecode;
	
	/**
	 * 请求时间
	 */
	private String requesttime;
	/**
	 * 请求内容
	 */
	private String requestcontent;
	/**
	 * 请求ip
	 */
	private String requestip;
	/**
	 * 返回结果编码
	 */
	private String returncode;
	/**
	 * 返回结果描述
	 */
	private String returnmessage;
	
	public String getFwsid() {
		return fwsid;
	}
	public void setFwsid(String fwsid) {
		this.fwsid = fwsid;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getInterfacecode() {
		return interfacecode;
	}
	public void setInterfacecode(String interfacecode) {
		this.interfacecode = interfacecode;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public String getRequestcontent() {
		return requestcontent;
	}
	public void setRequestcontent(String requestcontent) {
		this.requestcontent = requestcontent;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getReturnmessage() {
		return returnmessage;
	}

	public void setReturnmessage(String returnmessage) {
		this.returnmessage = returnmessage;
	}
	
	public GlobalInfo(String fwsid, String serviceid, String appid, String interfacecode, String requesttime,
			String requestcontent, String requestip, String returncode, String returnmessage) {
		super();
		this.fwsid = fwsid;
		this.serviceid = serviceid;
		this.appid = appid;
		this.interfacecode = interfacecode;
		this.requesttime = requesttime;
		this.requestcontent = requestcontent;
		this.requestip = requestip;
		this.returncode = returncode;
		this.returnmessage = returnmessage;
	}
	
	public GlobalInfo(){
		
	}
	
}
