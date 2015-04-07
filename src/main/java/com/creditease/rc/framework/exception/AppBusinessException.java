/**
 * 
 */
package com.creditease.rc.framework.exception;

import com.creditease.rc.framework.exception.annotation.AppExceptionMessage;

/**
 * Title:业务异常处理类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * Date: 2011-8-23
 * @author wangxinqiang
 * @version v1.0
 */
@AppExceptionMessage(errorCode = "RC-0201", appMessage = "抱歉，操作失败。请稍后再试或与管理员联系！")
public class AppBusinessException extends BaseAppRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param message 异常消息
	 */
	public AppBusinessException(String message) {
		super(message);
	}
	
	/**
	 * 构造函数
	 * @param throwable 异常线程
	 */
	public AppBusinessException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * 构造函数
	 * @param throwable 异常线程
	 * @param message 异常消息
	 */
	public AppBusinessException(Throwable throwable, String message) {
		super(message, throwable);
	}
	
}
