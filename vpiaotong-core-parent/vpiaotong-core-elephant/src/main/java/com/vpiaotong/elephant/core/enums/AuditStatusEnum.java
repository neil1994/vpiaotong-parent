package com.vpiaotong.elephant.core.enums;

/**
 * 审核状态
 * 
 * @author zhw
 */
public enum AuditStatusEnum {

    /** 待认证 */
    CHECKING("CHECKING", "待认证"),
    /** 认证通过 */
    CHECKPASS("CHECKPASS", "认证通过"),
    /** 认证失败 */
    NOPASS("NOPASS", "认证失败");

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

    AuditStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AuditStatusEnum getAuditStatusEnumByName(String key) {

        for (AuditStatusEnum item : values()) {
            if (item.getKey() .equals(key)) {
                return item;
            }
        }
        return null;
    }

}
