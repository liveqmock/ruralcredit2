package com.creditease.rc.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppCheckedException;

/**
 * Title: 异常处理类
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-12
 * @author: 解兵丰
 * @version: v1.0
 */
@Controller
public class ExceptionController {

	/**
	 * 抛出异常实验方法
	 */
	@RequestMapping("exception")
	public void createException(){
		throw new ClassCastException("Throw a ClassCastException！ It's a test.");
	}
	
	/**
	 * 处理异常实验类
	 * @param ex 异常对象
	 * @return 异常消息
	 */
	@ExceptionHandler(value={IOException.class,AppCheckedException.class})
	public @ResponseBody Message handleException(Exception ex){
		System.out.println("测试ok！");
		System.out.println(ex.getMessage());
		Message message = new Message();
		return message;
	}
	
}
