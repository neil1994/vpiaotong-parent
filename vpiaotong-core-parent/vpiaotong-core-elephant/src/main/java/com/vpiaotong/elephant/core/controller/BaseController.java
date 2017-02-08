package com.vpiaotong.elephant.core.controller;

import java.util.HashMap;
import java.util.Map;

import com.vpiaotong.elephant.core.controller.message.ReturnMessageInfo;
import com.vpiaotong.elephant.core.controller.message.ReturnMessageInfo.Message;
import com.vpiaotong.elephant.core.controller.message.ReturnStatusInfo;
import com.vpiaotong.elephant.core.controller.message.ReturnStatusInfo.MessageStatus;
import com.vpiaotong.elephant.core.utils.jackson.JsonConvertUtil;
import com.vpiaotong.elephant.core.utils.jackson.ObjectConvertStringException;

/**
 * 控制层基类文件
 * 
 * @author Acmen
 */
public class BaseController {

    /** 返回数据标识 */
    private static final String RESPONSE_DATA_CODE = "response";

    /** 返回数据描述信息标识 */
    protected static final String RESPONSE_MESSAGE_CODE = "message";

    /** 返回数据行标识 */
    protected static final String RESPONSE_DATALIST_CODE = "rows";

    /**
     * 封装返回成功数据
     * 
     * @param dataMap
     *            数据
     * @return JSON字符串
     * @throws ObjectConvertStringException
     *             转换异常
     */
    protected String packagingSuccessResult(Map<String, Object> dataMap) throws ObjectConvertStringException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY200), ReturnStatusInfo.getValue(MessageStatus.KEY200));
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY200), ReturnMessageInfo.getValue(Message.KEY200));
        if (null != dataMap) {
            resultMap.put(RESPONSE_DATA_CODE, dataMap);
        }
        return JsonConvertUtil.objectToJson(resultMap);
    }

    /**
     * 自定义返回信息(status:200:处理成功)
     * 
     * @param dataMap
     *            数据
     * @param message
     *            自定义处理信息
     * @return JSON字符串
     * @throws ObjectConvertStringException
     *             转换异常
     */
    protected String packagingSuccessResult(String message, Map<String, Object> dataMap) throws ObjectConvertStringException {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	if("FREQUENT".equals(message)){
    		resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY700), ReturnStatusInfo.getValue(MessageStatus.KEY700));
    		resultMap.put(ReturnMessageInfo.getKey(Message.KEY700), ReturnMessageInfo.getValue(Message.KEY700));
    	}else if("VCODEERROE".equals(message)){
    		resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY600), ReturnStatusInfo.getValue(MessageStatus.KEY600));
    		resultMap.put(ReturnMessageInfo.getKey(Message.KEY600), ReturnMessageInfo.getValue(Message.KEY600));
    	}else{
    		resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY200), ReturnStatusInfo.getValue(MessageStatus.KEY200));
    		resultMap.put(ReturnMessageInfo.getKey(Message.KEY200), ReturnMessageInfo.getValue(Message.KEY200));
    	}
    	
        if (null != dataMap) {
            resultMap.put(RESPONSE_DATA_CODE, dataMap);
        }
        return JsonConvertUtil.objectToJson(resultMap);
    }
   

    /**
     * 封装返回失败信息(status:500:失败)
     * 
     * @param message
     *            自定义处理信息
     * @return JSON字符串
     * @throws ObjectConvertStringException
     *             转换异常
     */
    protected String packagingFailureResult(String message) throws ObjectConvertStringException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY500), ReturnStatusInfo.getValue(MessageStatus.KEY500));
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY500), message);
        return JsonConvertUtil.objectToJson(resultMap);
    }

    /**
     * 自定义返回状态码及返回信息
     * 
     * @param status
     *            返回信息状态码枚举类
     * @param message
     *            返回信息枚举类
     * @param dataMap
     *            数据
     * @return JSON字符串
     * @throws ObjectConvertStringException
     *             转换异常
     */
    protected String packagingResult(MessageStatus status, Message message, Map<String, Object> dataMap)
            throws ObjectConvertStringException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ReturnStatusInfo.getKey(status), ReturnStatusInfo.getValue(status));
        resultMap.put(ReturnMessageInfo.getKey(message), ReturnMessageInfo.getValue(message));
        if (null != dataMap) {
            resultMap.put(RESPONSE_DATA_CODE, dataMap);
        }
        return JsonConvertUtil.objectToJson(resultMap);
    }

}
