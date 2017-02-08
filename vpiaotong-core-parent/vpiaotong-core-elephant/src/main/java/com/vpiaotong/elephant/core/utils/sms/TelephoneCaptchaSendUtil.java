package com.vpiaotong.elephant.core.utils.sms;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpiaotong.elephant.core.bean.DxDto;
import com.vpiaotong.elephant.core.bean.GlobalInfo;
import com.vpiaotong.elephant.core.bean.ResponseMsg;
import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;
import com.vpiaotong.elephant.core.utils.jackson.JsonConvertUtil;
import com.vpiaotong.elephant.core.utils.jackson.StringConvertObjectException;

/**
 * 发送验证码工具类
 * 
 * @author Acmen
 */
public class TelephoneCaptchaSendUtil {

    private static Logger log = LoggerFactory.getLogger(TelephoneCaptchaSendUtil.class);

    /**
     * 发送验证码
     * 
     * @Modify by kesong 更换短信网关 20160629
     * 
     * @param phoneNumber
     *            手机号码
     * @param code
     *            验证码内容
     * @return 成功||失败
     * @throws IOException
     * @throws StringConvertObjectException
     */
    public static boolean sendSMS(String phoneNumber, String code, SmsTemplateTypeEnum templateType) {
        try {
            PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
            // 根据类型获取配置文件内模板内容
          
            String content = config.getString(templateType.getKey());           
            String sendUrl = config.getString("sms.sendUrl");            
            log.info(content);
            List<PostParameter> postParams = new ArrayList<PostParameter>(6);

            postParams.add(new PostParameter("version", config.getString("sms.version")));
            postParams.add(new PostParameter("client_id", config.getString("sms.client_id")));
            postParams.add(new PostParameter("client_secret", config.getString("sms.client_secret")));            
            postParams.add(new PostParameter("globalInfo", JsonConvertUtil.objectToJson(setGlobalInfo())));
            postParams.add(new PostParameter("data", JsonConvertUtil.objectToJson(setDxDto(config.getInt("sms.autoLength"), config.getInt("sms.expire"), content, phoneNumber, code))));
            
            
            String postParam = encodeParameters(postParams);
            String response = callRemoteInterface(sendUrl, 300000, postParam);            
            ObjectMapper mapper = new ObjectMapper();
            ResponseMsg responseMsg =  mapper.readValue(response, ResponseMsg.class);
            log.info("给手机号:" + responseMsg.getData().getPhone() + "发送短信验证码：" + responseMsg.getData().getAutoCode());
            log.info("发送短信结果状态码：" + responseMsg.getGlobalInfo().getReturncode() + ",提示信息为：" + responseMsg.getGlobalInfo().getReturnmessage());
            if ("0".equals(responseMsg.getGlobalInfo().getReturncode()) ) {
                return true; 
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
        
    }

  

    /**
     * @param url
     *            请求地址
     * @param timeOut
     *            请求过期时间
     * @param postParam
     *            请求参数
     * @return 请求结果
     * @throws IOException
     */
    private static String callRemoteInterface(String url, int timeOut, String postParam) throws IOException {
        URL methodUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) methodUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        byte[] bytes = postParam.getBytes("UTF-8");
        out.write(bytes);
        out.flush();
        out.close();
        return IOUtils.toString(conn.getInputStream(),"UTF-8");
    }

    /**
     * 组装发送参数
     * 
     * @param postParams
     *            发送参数数组
     * @return 参数字符串
     */
    private static String encodeParameters(List<PostParameter> postParams) {
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < postParams.size(); j++) {
            if (j != 0) {
                buf.append("&");
            }
            try {
                buf.append(URLEncoder.encode(postParams.get(j).getFieldName(), "UTF-8")).append("=")
                        .append(URLEncoder.encode(postParams.get(j).getFieldValue(), "UTF-8"));
            }
            catch (java.io.UnsupportedEncodingException neverHappen) {
            }
        }
        return buf.toString();
    }

    /**
     * 验证码模板类型枚举类
     * 
     * @author zhaoTh
     */
    public enum SmsTemplateTypeEnum {
        /** 注册 */
        REGISTER("sms.register.content"),
        /** 接入商注册 */
        PROVIDERREGISTER("sms.providerRegister.content"),
        /** 找回密码 */
        RETRIEVEPASSWORD("sms.retrievePassword.content"),
        /** 修改密码 */
        CHANGEPASSWORD("sms.changepassword.content");

        /** 配置文件内的KEY */
        private final String key;

        /** 取配置文件内的KEY */
        public String getKey() {
            return key;
        }

        SmsTemplateTypeEnum(String key) {
            this.key = key;
        }
    }
    
	public static GlobalInfo setGlobalInfo(){
		GlobalInfo  globalInfo=new GlobalInfo();
		globalInfo.setAppid("app");
		globalInfo.setFwsid("fe07501238e84f7a967036eba3480abe");
		globalInfo.setInterfacecode("interfaceCode");
		return globalInfo;
	}
	
	public static DxDto setDxDto(int autoCodeLength, int ExpiryDate, String operation, String phone, String autoCode){
		DxDto dto=new DxDto();
		dto.setAutoCodeLength(autoCodeLength);
		dto.setExpiryDate(ExpiryDate);
		dto.setOperation(operation);
		dto.setPhone(phone);
		dto.setAutoCode(autoCode);
		return dto;
	}
    
    
    
}
