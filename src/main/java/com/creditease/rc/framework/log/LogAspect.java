package com.creditease.rc.framework.log;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.creditease.rc.util.JsonUtil;

/**
 * Title: 系统log记录器，记录Action，service，dao中方法的调用
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-15
 * @author: 解兵丰
 * @version: v1.0
 */
@Aspect
@Component
public class LogAspect {
	
	/**
	 * logger记录器
	 */
	private static final Log logger = LogFactory.getLog(LogAspect.class);

	/**
	 * Action切入点
	 */
	@Pointcut("execution(public * com.creditease.rc.action..*.*(..))")
	public void pointcutAction(){}
	
	/**
	 * Service切入点
	 */
	@Pointcut("execution(public * com.creditease.rc.service..*.*(..))")
	public void pointcutService(){}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  webService切入点 
	 * @version v1.0 
	 * 2013-3-27
	 */
	@Pointcut("execution(public * com.creditease.rc.app..*.*(..))")
	public void pointcutWS_Service(){}
	
	
	
	/**
	 * Dao切入点
	 */
	@Pointcut("execution(public * com.creditease.rc.dao..*.*(..))")
	public void pointcutDao(){}
	
	/**
	 * 记录方法执行开始
	 * @param jp 接入点对象
	 */
	@Before(value = "pointcutAction()||pointcutService()||pointcutDao()||pointcutWS_Service()")
	public void before(JoinPoint jp){
		Object target = jp.getTarget();
		Class clazz = target.getClass();
		Signature signature = jp.getSignature();
		String name = signature.getName();
		Object[] args = jp.getArgs();
		
		
		String matchName="com.creditease.rc.app";
		//得到类名称
		String declaringTypeName=signature.getDeclaringTypeName();
		int site=declaringTypeName.indexOf(matchName);
		if(site>=0){
			 for(int i=0;i<args.length;i++)
			  {
			   logger.info("webService接口名称:"+declaringTypeName);
			   logger.info("webService方法:"+name);
			   logger.info("webService方法入参:"+JsonUtil.getJackson(args[i]));
			  }
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sb.append(" ");
		sb.append(clazz.getName());
		sb.append(" ");
		sb.append(name);
		sb.append("  开始执行：");
		logger.info(sb.toString());
	}
	
	/**
	 * 记录方法执行结束
	 * @param jp 接入点对象
	 */
	@After(value = "pointcutAction()||pointcutService()||pointcutDao()||pointcutWS_Service()")
	public void after(JoinPoint jp){
		Object target = jp.getTarget();
		Class clazz = target.getClass();
		Signature signature = jp.getSignature();
		String name = signature.getName();
		Object[] args = jp.getArgs();
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sb.append(" ");
		sb.append(clazz.getName());
		sb.append(" ");
		sb.append(name);
		sb.append("  结束执行。");
		logger.info(sb.toString());		
	}
	
	/**
	 * 记录方法执行异常的log
	 * @param jp 接入点对象
	 * @param ex 异常线程对象
	 */
	@AfterThrowing(throwing = "ex", pointcut = "pointcutAction()||pointcutService()||pointcutDao()||pointcutWS_Service()")
	public void afterThrowing(JoinPoint jp, Throwable ex){
		Object target = jp.getTarget();
		Class clazz = target.getClass();
		Signature signature = jp.getSignature();
		String name = signature.getName();
		Object[] args = jp.getArgs();
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sb.append(" ");
		sb.append(clazz.getName());
		sb.append(" ");
		sb.append(name);
		sb.append("  抛出异常。");
		logger.error(sb.toString());		
	}
	
}
