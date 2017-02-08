package com.vpiaotong.elephant.core.utils.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vpiaotong.elephant.core.enums.AuditStatusEnum;

public class AuditStatusSerializer  extends JsonSerializer<String>{
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formatAuditStatus = "";
        AuditStatusEnum status = AuditStatusEnum.getAuditStatusEnumByName(value);
        if(status != null){
            formatAuditStatus = status.getValue();
        }
        
        jgen.writeString(formatAuditStatus);
    }
}
