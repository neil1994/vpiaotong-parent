package com.vpiaotong.elephant.core.utils.captcha;

import java.util.Arrays;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;

/**
 * 生成验证码工具类
 * 
 * @author QiXiang
 */
public final class CaptchaCodeUtil {

    /**
     * 私有化构造函数，防止没构建
     */
    private CaptchaCodeUtil() {
        throw new IllegalAccessError();
    }

    /** session内存储的验证码键值 */
    public static final String SESSION_CAPTCHA_KEY = "session_captcha_key";

    /** 备选字符集合(除去易混淆的数字0、数字1、字母l、字母o、字母O) */
    private static final char[] CODES = { '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     * 根据配置文件参数生成验证码
     * 
     * @return 验证码字符串
     */
    public static String generateCaptchaCode() {
        PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
        int length = Integer.parseInt(config.getString("captcha.length"));
        CaptchaCodeLevelEnum level = CaptchaCodeLevelEnum.keyConvertEnum(config.getString("captcha.level"));
        boolean isCanRepeat = Boolean.parseBoolean(config.getString("captcha.isCanRepeat"));
        return generateCaptchaCode(length, level, isCanRepeat);
    }

    /**
     * 产生长度和难度任意的验证码
     * 
     * @param length
     *            长度
     * @param level
     *            难度级别
     * @param isCanRepeat
     *            是否能够出现重复的字符
     * @return 验证码
     */
    private static String generateCaptchaCode(int length, CaptchaCodeLevelEnum level, boolean isCanRepeat) {
        char[] codeSequence = {};
        // 根据不同的难度截取字符数组
        switch (level) {
            case SIMPLE:
                codeSequence = Arrays.copyOfRange(CODES, 0, 8);
                break;
            case MEDIUM:
                codeSequence = Arrays.copyOfRange(CODES, 0, 32);
                break;
            case HARD:
                codeSequence = Arrays.copyOfRange(CODES, 0, 57);
                break;
            default:
                codeSequence = Arrays.copyOfRange(CODES, 0, 57);
                break;
        }
        // 字符集合长度
        int n = codeSequence.length;
        // 抛出运行时异常
        if (length > n && isCanRepeat == false) {
            throw new RuntimeException(String.format(
                    "调用CaptchaCodeUtil.generateVerifyCode(%1$s,%2$s,%3$s)出现异常，" + "当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s",
                    length, level, isCanRepeat, n));
        }
        // 存放抽取出来的字符
        char[] result = new char[length];
        // 判断能否出现重复的字符
        if (isCanRepeat) {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codeSequence[r];
            }
        }
        else {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codeSequence[r];
                codeSequence[r] = codeSequence[n - 1];
                n--;
            }
        }
        return String.valueOf(result);
    }
}
