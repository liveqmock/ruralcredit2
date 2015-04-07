package com.creditease.rc.framework.exception;

/**
 * Title: 系统异常处理类基类
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-13
 * @author: 解兵丰
 * @version: v1.0
 */
public abstract class BaseAppRuntimeException extends RuntimeException{

	/**
	 * 构造函数
	 */
	public BaseAppRuntimeException() {
		super();
	}
	
	/**
	 * 构造函数
	 * @param message 异常消息
	 */
	public BaseAppRuntimeException(String message) {
		super(message);
	}
	
	/**
	 * 构造函数
	 * @param cause 异常线程
	 * @param message 异常消息
	 */
	public BaseAppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 构造函数
	 * @param cause 异常线程
	 */
	public BaseAppRuntimeException(Throwable cause) {
		super(cause);
	}

}
