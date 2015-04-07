package com.creditease.rc.framework.exception;

import com.creditease.rc.framework.exception.annotation.AppExceptionMessage;

/**
 * Title: 系统中无法分类的异常处理类
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-13
 * @author: 解兵丰
 * @version: v1.0
 */
@AppExceptionMessage(errorCode = "RC-0901", appMessage = "抱歉，操作失败。请稍后再试或与管理员联系！")
public class AppOtherException extends BaseAppRuntimeException {
	
	private static final long serialVersionUID = -7048769695899941955L;

	/**
	 * 构造函数
	 * @param message 异常消息
	 */
	public AppOtherException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * @param throwable 异常线程
	 */
	public AppOtherException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * 构造函数
	 * @param throwable 异常线程
	 * @param message 异常消息
	 */
	public AppOtherException(Throwable throwable, String message) {
		super(message, throwable);
	}
	
}
