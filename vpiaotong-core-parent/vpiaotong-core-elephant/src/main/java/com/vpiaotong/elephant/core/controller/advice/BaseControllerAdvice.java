package com.vpiaotong.elephant.core.controller.advice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpiaotong.elephant.core.controller.interceptor.DateEditor;
import com.vpiaotong.elephant.core.controller.interceptor.StringEscapeEditor;
import com.vpiaotong.elephant.core.controller.message.ReturnMessageInfo;
import com.vpiaotong.elephant.core.controller.message.ReturnMessageInfo.Message;
import com.vpiaotong.elephant.core.controller.message.ReturnStatusInfo;
import com.vpiaotong.elephant.core.controller.message.ReturnStatusInfo.MessageStatus;
import com.vpiaotong.elephant.core.utils.exception.UploadException;
import com.vpiaotong.elephant.core.utils.jackson.ObjectConvertStringException;
import com.vpiaotong.elephant.core.utils.jackson.StringConvertObjectException;

/**
 * MVC控制器增强类
 * 
 * @author Acmen
 */
@ControllerAdvice
public class BaseControllerAdvice {

    private static Logger log = LoggerFactory.getLogger(BaseControllerAdvice.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /** 防止XSS攻击 */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
        /** 日期转换 */
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * MVC返回json转换异常
     * 
     * @param response
     *            http响应
     * @throws Exception 
     */
    @ExceptionHandler({ ObjectConvertStringException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void processJson2StringException(Exception e, HttpServletResponse response) throws Exception {
        log.error(e.getMessage(), e);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 返回500错误
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY500), ReturnStatusInfo.getValue(MessageStatus.KEY500));
        // 返回500描述信息
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY500), ReturnMessageInfo.getValue(Message.KEY500));
        writeValue(response, resultMap);
    }

    /**
     * 请求出现语法错误（参数转JSON对象异常）
     * 
     * @param response
     *            http响应
     * @throws IOException
     */
    @ExceptionHandler({ StringConvertObjectException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processString2JsonException(Exception e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 返回400错误
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY400), ReturnStatusInfo.getValue(MessageStatus.KEY400));
        // 返回400描述信息
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY400), ReturnMessageInfo.getValue(Message.KEY400));
        writeValue(response, resultMap);
    }

    /**
     * 处理运行期异常
     * 
     * @param response
     *            http响应
     * @throws IOException
     */
    @ExceptionHandler({ RuntimeException.class, IllegalArgumentException.class, SQLException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void processRunTimeException(Exception e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 返回500错误
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY500), ReturnStatusInfo.getValue(MessageStatus.KEY500));
        // 返回500描述信息
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY500), ReturnMessageInfo.getValue(Message.KEY500));
        writeValue(response, resultMap);
    }

  //  @ExceptionHandler({ UploadException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void processUploadException(Exception e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 返回500错误
        resultMap.put(ReturnStatusInfo.getKey(MessageStatus.KEY500), ReturnStatusInfo.getValue(MessageStatus.KEY500));
        // 返回500描述信息
        resultMap.put(ReturnMessageInfo.getKey(Message.KEY500), e.getMessage());
        writeValue(response, resultMap);
        throw new ConversionNotSupportedException(resultMap, null, e);
    }

    /**
     * 返回信息
     * 
     * @param response
     *            http响应
     * @param value
     *            待转换JSON数据
     * @throws IOException
     */
    private void writeValue(HttpServletResponse response, Object value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), value);
    }
}
