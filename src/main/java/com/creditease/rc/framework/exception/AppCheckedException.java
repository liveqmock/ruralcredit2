package com.creditease.rc.framework.exception;

import com.creditease.rc.framework.exception.annotation.AppExceptionMessage;

/**
 * Title: Controller层异常处理类，用于Controller中给Service层准备数据时的Exception，eg. 数据类型转换错误，输入数据为空，转换成数字时输入数据非法，等等。
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-13
 * @author: 解兵丰
 * @version: v1.0
 */
@AppExceptionMessage(errorCode = "RC-0101", appMessage = "参数非法，请检查输入的参数是否合法且正确。")
public class AppCheckedException extends BaseAppRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2862448968227755806L;

	/**
	 * 构造函数
	 * @param message 异常消息
	 */
	public AppCheckedException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * @param throwable 异常线程
	 */
	public AppCheckedException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * 构造函数
	 * @param throwable 异常线程
	 * @param message 异常消息
	 */
	public AppCheckedException(Throwable throwable, String message) {
		super(message, throwable);
	}
	
}
