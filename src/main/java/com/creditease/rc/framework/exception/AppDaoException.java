package com.creditease.rc.framework.exception;

import java.sql.SQLException;

import com.creditease.rc.framework.exception.annotation.AppExceptionMessage;

/**
 * Title:Dao层异常处理类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * Date: 2011-6-30
 * @author wangxinqiang
 * @version v1.0
 */
@AppExceptionMessage(errorCode = "RC-0301", appMessage = "数据库操作失败。请稍后再试或与管理员联系！")
public class AppDaoException extends BaseAppRuntimeException{
	
	private static final long serialVersionUID = -327129388553301163L;
	
	private SQLException sqle;
	
	/**
	 * 构造函数
	 */
	public AppDaoException(){
		super();
	}
	
	/**
	 * 构造函数
	 * @param message 异常消息
	 */
	public AppDaoException(String message){
		super(message);
	}
	
	/**
	 * 构造函数
	 * @param sqle sql异常对象
	 */
	public AppDaoException(SQLException sqle){
		super(String.valueOf(sqle.getErrorCode()));
		this.sqle = sqle;
		this.setStackTrace(sqle.getStackTrace());
	}
	
	/**
	 * 构造函数
	 * @param ex 异常对象
	 */
	public AppDaoException(Exception ex){
		super(ex);
	}
	
	/**
	 * 构造函数
	 * @param ex 异常线程
	 * @param message 异常消息
	 */
	public AppDaoException(Exception ex, String message){
		super(message, ex);
	}
	
}
