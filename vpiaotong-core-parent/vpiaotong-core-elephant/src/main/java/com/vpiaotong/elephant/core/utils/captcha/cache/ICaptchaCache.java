package com.vpiaotong.elephant.core.utils.captcha.cache;

/**
 * 验证码缓存接口
 * 
 * @author Acmen
 */
public interface ICaptchaCache {

    /**
     * 缓存验证码CODE
     * 
     * @param sessionKey
     *            session内缓存对象的键值
     * @param object
     *            缓存对象
     */
    void cacheCaptchaCode(String sessionKey, Object object);

    /**
     * 删除缓存的验证码
     * 
     * @param sessionKey
     *            session内缓存对象的键值
     */
    void clearCaptcha(String sessionKey);

    /**
     * 从缓存内取验证码
     * 
     * @param sessionKey
     *            session内缓存对象的键值
     * @return 验证码CODE
     */
    Object getCaptchaCodeFromCache(String sessionKey);
}
