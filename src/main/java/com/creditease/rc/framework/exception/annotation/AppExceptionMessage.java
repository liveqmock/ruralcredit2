package com.creditease.rc.framework.exception.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 系统异常处理类的消息，包括errorCode和自定义消息
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-13
 * @author: 解兵丰
 * @version: v1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppExceptionMessage {
	/**
	 * 获取异常代号
	 * @return
	 */
	public String errorCode();
	
	/**
	 * 获取异常应用消息
	 * @return
	 */
	public String appMessage();

}
