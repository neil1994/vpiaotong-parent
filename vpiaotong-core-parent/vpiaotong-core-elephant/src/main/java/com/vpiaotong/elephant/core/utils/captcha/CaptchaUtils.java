package com.vpiaotong.elephant.core.utils.captcha;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.vpiaotong.elephant.core.utils.captcha.cache.ICaptchaCache;
import com.vpiaotong.elephant.core.utils.spring.SpringBeansUtil;

/**
 * 图形验证码工具类
 * 
 * @author Acmen
 */
public final class CaptchaUtils {

    private static ICaptchaCache captchaCache;

    static {
        captchaCache = (ICaptchaCache) SpringBeansUtil.getBean("captchaCache");
    }

    /**
     * 输出图形验证码
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @throws IOException
     */
    public static void outputCaptchaImage(HttpServletResponse response) throws IOException {
        String captchaCode = generateCaptchaCode();
        writeCaptchaImage(captchaCode, response);
    }

    /**
     * 生成图形验证码CODE
     * 
     * @param request
     *            HttpServletRequest
     * @return 验证码CODE
     */
    public static String generateCaptchaCode() {
        // 生成验证码CODE
        String captchaCode = CaptchaCodeUtil.generateCaptchaCode();
        // 缓存生成的验证码CODE等待校验
        captchaCache.cacheCaptchaCode(CaptchaCodeUtil.SESSION_CAPTCHA_KEY, captchaCode);
        return captchaCode;
    }

    /**
     * 把图形验证码CODE以图片形式输出
     * 
     * @param captchaCode
     *            验证码CODE
     * @param response
     *            HttpServletResponse
     * @throws IOException
     */
    public static void writeCaptchaImage(String captchaCode, HttpServletResponse response) throws IOException {
        // 设置响应内容
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setDateHeader("Expire", 0);

        // 输出验证码图片
        ServletOutputStream out = response.getOutputStream();
        try {
            ImageIO.write(CaptchaImageUtil.createDisturbImage(captchaCode), "JPEG", response.getOutputStream());
            out.flush();
        }
        finally {
            out.close();
        }
    }

    /**
     * 校验用户输入的验证码是否正确
     * 
     * @param inputCaptchaCode
     *            户输入的验证码
     * @return true||false
     */
    public static boolean checkCaptchaCode(String inputCaptchaCode) {
        if (null != inputCaptchaCode) {
            // 去空格、空白字符串、制表符、换行符
            inputCaptchaCode = inputCaptchaCode.replaceAll("\\s*|\t|\r|\n", "");
            if (StringUtils.isEmpty(inputCaptchaCode)) {
                return false;
            }
            Object captchaCode = captchaCache.getCaptchaCodeFromCache(CaptchaCodeUtil.SESSION_CAPTCHA_KEY);
            if (null != captchaCode) {
                return String.valueOf(captchaCode).equalsIgnoreCase(inputCaptchaCode);
            }
        }
        return false;
    }

    /**
     * 防止构建
     */
    private CaptchaUtils() {
        throw new IllegalAccessError();
    }
}
