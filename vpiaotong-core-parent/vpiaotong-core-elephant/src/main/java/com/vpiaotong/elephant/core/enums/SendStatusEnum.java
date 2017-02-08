package com.vpiaotong.elephant.core.enums;


public enum SendStatusEnum {
    /** 验证通过 */
    SUCCESS("SUCCESS", "短信发送成功"),
    /** 验证码失效 */
    FAIL("FAIL", "短信发送失败"),
    /** 发送频繁 */
    FREQUENT("FREQUENT", "短信发送太频繁"),
	/** 图片验证码错误 */
	VCODEERROE("VCODEERROE", "图片验证码错误");

    /** 名字 */
    private final String name;

    /** 值 */
    private final String value;
    
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    SendStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
