package com.vpiaotong.elephant.core.utils.sms;

import java.io.Serializable;

/**
 * 传输参数对象
 * 
 * @author Acmen
 */
@SuppressWarnings("serial")
public class PostParameter implements Serializable {

    /** 字段名 */
    private String fieldName;

    /** 字段值 */
    private String fieldValue;

    public PostParameter(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
