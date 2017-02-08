package com.vpiaotong.elephant.core.utils.email;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;

/**
 * 
 * 描述信息：邮箱发送配置文件参数读取
 *
 * @author 腾金玉
 * @version 1.0 Created on 2016年9月18日 下午1:24:42
 */
public class EmailProperties {
		
	/**
	 * 
	 * <p>邮箱参数设置</p>
	 * 
	 * @return Boolean
	 * @author: 腾金玉
	 * @date: Created on 2016年9月18日 上午10:45:08
	 */
	public static EmailBean setEmailProperties() {
	    	
        PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
        String emailSubject = config.getString("email.subject");
        String emailSendname = config.getString("email.sendname");
        String emailSmtpfrommail = config.getString("email.smtpfrommail");
        String emailPwd = config.getString("email.pwd");
        String emailHost = config.getString("email.host");
       
        EmailBean bean = new EmailBean();
        if(emailSubject!=null && emailSubject !="" && 
           emailSendname!=null && emailSendname !="" &&
           emailSmtpfrommail!=null && emailSmtpfrommail !="" &&
           emailPwd!=null && emailPwd !="" &&
 		   emailHost!=null && emailHost != ""){
        	
        	//设置值
        	bean.setSubject(emailSubject);
        	bean.setSendname(emailSendname);
        	bean.setSmtpfrommail(emailSmtpfrommail);
        	bean.setPwd(emailPwd);
        	bean.setHost(emailHost);
        	
        	
        	//设置读取成功标志
        	bean.setFlag(true);
        	return bean;
        }else{
        	//设置读取失败返回参数
        	bean.setFlag(false);
        	return bean ; 
        }
	}
}
