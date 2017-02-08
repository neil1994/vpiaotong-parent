package com.vpiaotong.elephant.core.utils.sms;

import java.util.Date;

import com.vpiaotong.elephant.core.enums.SendStatusEnum;
import com.vpiaotong.elephant.core.enums.VerifyCodeEnum;
import com.vpiaotong.elephant.core.utils.calendar.CalendarUtil;
import com.vpiaotong.elephant.core.utils.captcha.cache.ICaptchaCache;
import com.vpiaotong.elephant.core.utils.sms.TelephoneCaptchaSendUtil.SmsTemplateTypeEnum;
import com.vpiaotong.elephant.core.utils.spring.SpringBeansUtil;

/**
 * 发送手机验证码工具类
 * 
 * @author Acmen
 */
public class TelephoneCaptchaUtils {

    private static ICaptchaCache captchaCache;

    static {
        captchaCache = (ICaptchaCache) SpringBeansUtil.getBean("captchaCache");
    }

    /**
     * 发送验证码并且校验验证码是否过期
     * 
     * @param phone
     * @return
     */
    public static String validateCode(String phone, SmsTemplateTypeEnum type) {
        // 从session里面获取验证码
        Object captcha = captchaCache.getCaptchaCodeFromCache(type.getKey());
        // 要发送的验证码
        String code = RandomUtils.generateNumString(4);
        //String code = "0000";// 测试用
        // 发送验证码时间
        long regTime = CalendarUtil.getUnixtime(new Date());
        // 发送验证码过期起始时间
        long validStartTime = regTime;
        TelephoneCaptcha reg = null;
        if (captcha != null) {
            reg = (TelephoneCaptcha) captcha;
            // 判读是否是同一个类型
            if (reg.getType().equals(type)) {
                // 判断是否在间隔期1分钟内连续发送信息（防止恶意调用后台发送短信端口）
                if (reg.isInterval()) {
                    return SendStatusEnum.FREQUENT.getName();
                }
                // 校验短信验证码是否过期
                if (!reg.isTimeout()) {
                    // 未过期需重新发送刚才的验证码
                    code = reg.getCaptcha();
                    // 验证码有效时间不变
                    validStartTime = reg.getRegCodeValidStartTime();
                }
            }
        }
        return sendMessage(regTime, validStartTime, phone, code, type);
    }

    public static String sendMessage(long regTime, long validStartTime, String phone, String code,
            SmsTemplateTypeEnum type) {
        if (TelephoneCaptchaSendUtil.sendSMS(phone, code, type)) {
            TelephoneCaptcha msgCode = new TelephoneCaptcha().setCaptcha(code).setRegTime(regTime)
                    .setRegCodeValidStartTime(validStartTime).setPhone(phone).setType(type);
            captchaCache.cacheCaptchaCode(type.getKey(), msgCode);
            return SendStatusEnum.SUCCESS.getName();
        }
        else {
            return SendStatusEnum.FAIL.getName();
        }
    }

    /**
     * 注册时校验验证码是否已经过期，并且校验是否是发验证码时的手机号 如果过期返回相应的提示信息
     * 
     * @param phone
     * @return
     */
    public synchronized static VerifyCodeEnum validateRegistCode(String phone, String captchaAfter,
            SmsTemplateTypeEnum type) {
        TelephoneCaptcha captcha = (TelephoneCaptcha) captchaCache.getCaptchaCodeFromCache(type.getKey());
        if (captcha == null) {
            return VerifyCodeEnum.INVAILD;
        }
        else if (captcha.isTimeout()) {
            return VerifyCodeEnum.INVAILD;
        }
        else if (!captcha.getPhone().equals(phone)) {
            return VerifyCodeEnum.PHONEERROR;
        }
        else if (!captcha.getCaptcha().equals(captchaAfter)) {
            return VerifyCodeEnum.NOPASS;
        }
        // 校验通过清除缓存的验证码信息
        clearCacheCaptcha(type);
        return VerifyCodeEnum.PASS;
    }

    /**
     * 清除缓存的验证码
     * 
     * @param type
     *            发送验证码类型
     */
    public static void clearCacheCaptcha(SmsTemplateTypeEnum type) {
        captchaCache.clearCaptcha(type.getKey());
    }
}
