/**
 * 
 */
package com.creditease.rc.framework.exception.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppOtherException;
import com.creditease.rc.framework.exception.BaseAppRuntimeException;
import com.creditease.rc.framework.exception.annotation.AppExceptionMessage;

/**
 * Title: 异常拦截器
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-13
 * @author: 解兵丰
 * @version: v1.0
 */
@Component
public class ExceptionInterceptor implements HandlerExceptionResolver {

	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(ExceptionInterceptor.class);
	
	/**
	 * 实现异常处理接口
	 * @param request http请求
	 * @param response http相应
	 * @param object 对象
	 * @param ex 异常对象
	 * @return ModelAndView
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {

		// 处理所有异常的返回页面消息
		Message message = handleAppException(ex);
		
		// 异常处理的log
		logger.error(message.getMsg(), ex);
		
		// 返回页面的json消息
		String json_message = "{\"msg\":\"" + message.getMsg() +  "\",\"success\":false}";
        try {
        	//Content-Type = 'application/json;charset=UTF-8'
        	response.addHeader("Content-Type", "application/json;charset=UTF-8");
        	response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_message);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return new ModelAndView();  
	}

	/**
	 * 处理所有异常的返回页面消息
	 * @param ex 异常对象
	 * @return Message
	 */
	private Message handleAppException(Exception ex) {
		Message message = new Message();
		if(ex instanceof BaseAppRuntimeException){
			AppExceptionMessage appExceptionMessage = AnnotationUtils.findAnnotation(ex.getClass(), AppExceptionMessage.class);
			if(appExceptionMessage != null){
				message.setMsg(appExceptionMessage.errorCode() + ": " + appExceptionMessage.appMessage());
			}
		} else {
			// 非系统异常都转化为系统异常消息（转化为AppOtherException的异常消息）
			AppExceptionMessage appExceptionMessage = AnnotationUtils.findAnnotation(AppOtherException.class, AppExceptionMessage.class);
			if(appExceptionMessage != null){
				message.setMsg(appExceptionMessage.errorCode() + ": " + appExceptionMessage.appMessage());
			}
		}
		return message;
	}

}
