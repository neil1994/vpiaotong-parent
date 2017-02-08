package com.vpiaotong.elephant.core.utils.captcha;

/**
 * 验证码生成难度
 * 
 * @author QiXiang
 */
public enum CaptchaCodeLevelEnum {
    /** Simple只包含数字 */
    SIMPLE("SIMPLE"),
    /** Medium包含数字和小写英文 */
    MEDIUM("MEDIUM"),
    /** Hard包含数字和大小写英文 */
    HARD("HARD");

    /** 键 */
    private final String key;

    public String getKey() {
        return key;
    }

    CaptchaCodeLevelEnum(String key) {
        this.key = key;
    }

    /**
     * 键转枚举
     * 
     * @param key
     *            键
     * @return CaptchaCodeLevel
     */
    public static CaptchaCodeLevelEnum keyConvertEnum(String key) {
        if (null != key) {
            for (CaptchaCodeLevelEnum level : CaptchaCodeLevelEnum.values()) {
                if (level.getKey().equals(key)) {
                    return level;
                }
            }
        }
        return null;
    }
}
