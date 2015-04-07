/**
 * Title:IRollbackService.java  
 * Description:  
 */
package com.creditease.rc.service;

import com.creditease.rc.domain.Message;

/**
 * Title:IRollbackService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-5-15 
 */
public interface IRollbackService {

	/**
	 * 
	 * @author 韩大年  
	 * @Description:  所有回退调用的方法
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-15
	 */
	public Message rollback(Long creditapplicationId);
}
