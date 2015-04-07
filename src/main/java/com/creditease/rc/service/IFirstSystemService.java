package com.creditease.rc.service;

import com.creditease.rc.domain.Message;

/**
 * 一期数据查询
 * Title:IFirstSystemService.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-7上午10:43:01
 * @author wyf
 * @version v2.0
 */
public interface IFirstSystemService {
	/**
	 * 判断新增客户是否在一期中有未还完 true-没有
	* @author wyf   
	* @param credentialsNumber 
	* @return  Message 
	* @throws Exception    
	* 
	 */
	public Message queryByUndone(String credentialsNumber)throws Exception;
}
