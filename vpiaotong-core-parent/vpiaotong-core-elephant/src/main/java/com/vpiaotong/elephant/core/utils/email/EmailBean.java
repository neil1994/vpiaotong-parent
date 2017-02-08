package com.vpiaotong.elephant.core.utils.email;

/**
 * 
 * 描述信息：邮箱发送BEAN
 *
 * @author 腾金玉
 * @version 1.0 Created on 2016年9月18日 下午1:24:22
 */
public class EmailBean {

	private String subject ;  //邮件的主题
	private String content;	  //邮件的内容
	private String sendname ;  //邮件发送者姓名
	
	private String smtpfrommail;  //发送邮件方账号  
	private String pwd ; //发送邮件方密码  
	private String host ; //发送邮件方端口  
	
	private boolean flag ; //设置邮箱参数成功失败标志
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSendname() {
		return sendname;
	}
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}
	public String getSmtpfrommail() {
		return smtpfrommail;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setSmtpfrommail(String smtpfrommail) {
		this.smtpfrommail = smtpfrommail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
