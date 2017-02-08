package com.vpiaotong.elephant.core.utils.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vpiaotong.elephant.core.enums.UserStatusEnum;
/**
 * 用户状态转换
 * @author yinyx1
 *
 */
public class UserStatusSerializer extends JsonSerializer<String>{
    
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formatUserStatus = "";
        UserStatusEnum status = UserStatusEnum.getUserStatusEnumByName(value);
        if(status != null){
        switch (status) {
            case NORMAL:
                formatUserStatus = "正常";
                break;
            case DISABLED:
                formatUserStatus = "禁用";
                break;
            case DELETE:
                formatUserStatus = "删除";
                break;
            default:
                break;
        }
        }
        
        jgen.writeString(formatUserStatus);
    }

}
