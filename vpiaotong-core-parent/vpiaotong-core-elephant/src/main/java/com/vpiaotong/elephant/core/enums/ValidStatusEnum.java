package com.vpiaotong.elephant.core.enums;

/**
 * 有效状态:key改为数字，统一标准
 * 
 * @author KJX
 */
public enum ValidStatusEnum {

    /** 有效 */
    Y("1", "有效"),

    /** 无效 */
    N("0", "无效");

    /** 名字 */
    private final String key;

    /** 值 */
    private final String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    ValidStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
