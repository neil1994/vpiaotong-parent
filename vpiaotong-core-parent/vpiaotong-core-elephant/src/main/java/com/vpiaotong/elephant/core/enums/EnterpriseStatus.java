package com.vpiaotong.elephant.core.enums;

/**
 * 
 * 企业端的共用枚举类
 *
 * @author 张双超
 * @version 1.0 Created on 2016年8月11日 上午11:47:37
 */
public enum EnterpriseStatus {

	CREDITPATH("creditPath", "社会信用代码类型"),
	TAXREGISTERPATH("taxRegisterPath", "税务登记证文件地址类型"),
	BUSINESSLICENCEPATH("businessLicencePath", "营业执照文件地址类型"),
	CONTRACTSCARDPATH1("contractsCardPath1", "联系身份证复印件正面地址类型"),
	CONTRACTSCARDPATH2("contractsCardPath2", "联系身份证复印件反面地址类型");

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

    EnterpriseStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static EnterpriseStatus getEnterpriseStatusEnumByName(String key) {

        for (EnterpriseStatus item : values()) {
            if (item.getKey() .equals(key)) {
                return item;
            }
        }
        return null;
    }

}
