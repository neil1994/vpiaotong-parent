package com.vpiaotong.elephant.core.utils.sms;

public class SendResponseMsg {

    /** 返回代码 */
    private String errorCode;

    /** 返回信息 */
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum statusCodeEnum {
        /** 接口调用成功 */
        CODE_200("200"),
        /** 接口调用失败 */
        CODE_300("300"),
        /** 接口调用参数不合法 */
        CODE_301("301"),
        /** 服务器异常 */
        CODE_302("302");

        /** 状态码 */
        private final String code;

        public String getCode() {
            return code;
        }

        statusCodeEnum(String code) {
            this.code = code;
        }

        public static statusCodeEnum getSendResult(String responseCode) {
            for (statusCodeEnum statusCode : values()) {
                if (statusCode.code.equals(responseCode)) {
                    return statusCode;
                }
            }
            return null;
        }

    }
}
