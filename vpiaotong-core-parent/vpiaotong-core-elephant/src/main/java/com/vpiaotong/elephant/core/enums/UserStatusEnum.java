package com.vpiaotong.elephant.core.enums;

public enum UserStatusEnum {

    /** 正常 */
    NORMAL("NORMAL"),
    /** 关闭（封号） */
    DISABLED("DISABLED"),
    /** 删除 */
    DELETE("DELETE");

    /** 账号状态码 */
    private final String statusCode;

    /**
     * 取账号状态CODE
     * 
     * @return statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    UserStatusEnum(String statusCode) {
        this.statusCode = statusCode;
    }

    public static UserStatusEnum getUserStatusEnumByName(String name) {

        for (UserStatusEnum item : values()) {
            if (item.statusCode.equals(name)) {
                return item;
            }
        }
        return null;
    }



}
