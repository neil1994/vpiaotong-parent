package com.vpiaotong.elephant.core.utils.sms;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.vpiaotong.elephant.core.utils.calendar.CalendarUtil;
import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;
import com.vpiaotong.elephant.core.utils.sms.TelephoneCaptchaSendUtil.SmsTemplateTypeEnum;

/**
 * 验证码
 * 
 * @author QX
 */
public class TelephoneCaptcha implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4664601379673844436L;

    /** 验证码 */
    private String captcha;

    /** 发送时间 */
    private long regTime;

    /** 发送时间 */
    private long regCodeValidStartTime;

    /** 注册手机号码 */
    private String phone;
    
    /** 发送类型 */
    private SmsTemplateTypeEnum type;
    
    public SmsTemplateTypeEnum getType() {
        return type;
    }
   
    public TelephoneCaptcha setType(SmsTemplateTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getCaptcha() {
        return captcha;
    }

    public TelephoneCaptcha setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    public long getRegTime() {
        return regTime;
    }

    public TelephoneCaptcha setRegTime(long regTime) {
        this.regTime = regTime;
        return this;
    }

    public long getRegCodeValidStartTime() {
        return regCodeValidStartTime;
    }

    public TelephoneCaptcha setRegCodeValidStartTime(long regCodeValidStartTime) {
        this.regCodeValidStartTime = regCodeValidStartTime;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public TelephoneCaptcha setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * 验证码是否过期，过期时间30分钟(true:过期，false：不过期)
     * 
     * @return boolean
     */
    public boolean isTimeout() {
    	
    	// 根据类型获取配置文件内模板内容
    	PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
    	// 获取短信验证码有效时间
        String validTime = config.getString("telCaptcha.validTime");     
        // 取当前系统时间
        long nowTime = CalendarUtil.getUnixtime(new Date());
        // 与注册验证码发送时间比较
        long betweenTimes = nowTime - regCodeValidStartTime;
        long minutes = betweenTimes / 60;
        if (minutes >= Integer.parseInt(validTime)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否在限制时间1分钟内重复发送验证码(true:在间隔期内，false：不在间隔期内)
     * 
     * @return boolean
     */
    public boolean isInterval() {
    	// 根据类型获取配置文件内模板内容
    	PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
    	// 获取短信验证码发送频率
        String sendHZ = config.getString("telCaptcha.sendHZ");
    	
        // 取当前系统时间
        long nowTime = CalendarUtil.getUnixtime(new Date());
        // 与注册验证码发送时间比较
        long betweenTimes = nowTime - regTime;
        long minutes = betweenTimes / 60;
        if (minutes < Integer.parseInt(sendHZ)) {
            return true;
        }
        return false;
    }
}