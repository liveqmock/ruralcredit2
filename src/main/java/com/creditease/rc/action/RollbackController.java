/**
 * Title:RollbackController.java  
 * Description:  
 */
package com.creditease.rc.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IRollbackService;

/**
 * Title:RollbackController.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-5-17 
 */
@Controller
@RequestMapping("rollbackController")
public class RollbackController {

	/**
	 * @Description 默认构造器 
	 */
	public RollbackController() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IRollbackService rollbackService;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  所有的回退功能
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-17
	 */
	@RequestMapping("rollback")
	@ResponseBody
	public Message rollback(Long creditapplicationId){
		return rollbackService.rollback(creditapplicationId);
	}

}
