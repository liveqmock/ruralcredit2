package com.creditease.rc.framework.pager.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.creditease.rc.framework.pager.Dialect;

/**
 * Title: 分页执行器（LimitSqlExecutor）的dialect 注解配置
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-26
 * @author: 解兵丰
 * @version: v1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBDialect {
	
	/**
	 * annotation属性定义
	 * @return 
	 */
	public Class<? extends Dialect> value(); 
}
