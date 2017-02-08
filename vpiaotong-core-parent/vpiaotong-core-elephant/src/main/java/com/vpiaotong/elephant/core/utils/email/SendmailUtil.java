package com.vpiaotong.elephant.core.utils.email;

import java.io.File;  
import java.util.Date;  
import java.util.Properties;  
  
import javax.activation.DataHandler;  
import javax.activation.DataSource;  
import javax.activation.FileDataSource;  
import javax.mail.BodyPart;  
import javax.mail.Message;  
import javax.mail.Multipart;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;

/**
 * 
 * 描述信息：邮箱发送
 *
 * @author 腾金玉
 * @version 1.0 Created on 2016年9月18日 下午1:23:52
 */
@SuppressWarnings("restriction")
public class SendmailUtil {  
    
	/**
	 * 
	 * <p>功能实现描述</p>
	 * 
	 * @param bean   邮件参数
	 * @param toMail 发送的邮箱
	 * @param files	附件的本地路径
	 * @return boolean 成功但会true
	 * @author: 腾金玉
	 * @date: Created on 2016年9月19日 下午2:13:33
	 */
    public static boolean sendMail(EmailBean bean, String toMail,  
             String... files) {  
        boolean isFlag = false;  
        try {  
  
            Properties props = new Properties();  
            props.put("mail.smtp.host", bean.getHost());  
            props.put("mail.smtp.auth", "true");  
            Session session = Session.getDefaultInstance(props);  
            session.setDebug(false);  
  
            MimeMessage message = new MimeMessage(session);  
            try {  
                message.setFrom(new InternetAddress(bean.getSmtpfrommail(), "大象慧云"));  
                message.addRecipient(Message.RecipientType.TO,  
                        new InternetAddress(toMail));  
                message.setSubject(bean.getSubject());  
                message.addHeader("charset", "UTF-8");  
                  
                /*添加正文内容*/  
                Multipart multipart = new MimeMultipart();
                BodyPart contentPart = new MimeBodyPart();  
                contentPart.setText(bean.getContent());  
  
                contentPart.setHeader("Content-Type", "text/html; charset=UTF-8");  
                multipart.addBodyPart(contentPart);  
                  
                /*添加附件*/  
                for (String file : files) {  
                	File usFile = new File(file);  
                    MimeBodyPart fileBody = new MimeBodyPart();  
                    DataSource source = new FileDataSource(file);  
                    fileBody.setDataHandler(new DataHandler(source));  
					sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();  
                    fileBody.setFileName("=?UTF-8?B?"  
                            + enc.encode(usFile.getName().getBytes()) + "?=");  
                    multipart.addBodyPart(fileBody);  
                }  
  
                message.setContent(multipart);  
                message.setSentDate(new Date());  
                message.saveChanges();  
                Transport transport = session.getTransport("smtp");  
  
                transport.connect(bean.getHost(), bean.getSmtpfrommail(), bean.getPwd());  
                transport.sendMessage(message, message.getAllRecipients());  
                transport.close();  
                isFlag = true;  
            } catch (Exception e) {  
                isFlag = false;  
                e.printStackTrace();
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return isFlag;  
    }  

}  