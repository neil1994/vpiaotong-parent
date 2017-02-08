package com.vpiaotong.elephant.core.bean;

/*
 * 
 *  <p>短信返回报文</p>
 *
 * @author kesong
 * @version 1.0 Created on 2016年6月29日 下午4:31:49
 */

public class ResponseMsg {
	
	private GlobalInfo globalInfo;
	
	private DxDto data;

	public GlobalInfo getGlobalInfo() {
		return globalInfo;
	}

	public void setGlobalInfo(GlobalInfo globalInfo) {
		this.globalInfo = globalInfo;
	}

	public DxDto getData() {
		return data;
	}

	public void setData(DxDto data) {
		this.data = data;
	}
	
	

}
