package com.vpiaotong.elephant.core.enums;


public enum VerifyCodeEnum {
    /** 验证通过 */
    PASS("PASS", "短信验证通过"),
    /** 验证码失效 */
    INVAILD("INVAILD", "短信验证码失效"),
    /** 短信验证码错误 */
    NOPASS("NOPASS", "短信验证码错误"),
    /** 手机改变 */
    PHONEERROR("PHONEERROR", "手机改变，请重新获取验证码");

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

    VerifyCodeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
