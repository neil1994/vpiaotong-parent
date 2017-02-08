package com.vpiaotong.elephant.core.enums;

/**
 *  资质审核状态
 * 
 * @author zhw
 */
public enum StatusEnum {

	
    /** 待审核 */
    CHECKING("1", "待审核"),
    /** 审核通过 */
    CHECKPASS("2", "审核通过"),
    /** 审核失败 */
    NOPASS("3", "审核失败"),
    /** 删除 */
    DELETE("4", "禁用");

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

    StatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 通过key获取对象
     * 
     * @param key
     * @return
     */
    public static StatusEnum getStatusEnumByName(String key) {
        for (StatusEnum item : values()) {
            if (item.getKey().equals(key) ) {
                return item;
            }
        }
        return null;
    }
}
