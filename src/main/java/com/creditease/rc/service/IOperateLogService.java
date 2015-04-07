/**
 * Title:IOperateLogService.java  
 * Description:  
 */
package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.OperateLog;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.OperateLogVO;

/**
 * Title:IOperateLogService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-11 
 */
public interface IOperateLogService {

	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日志插入
	 * @param operateLog
	 * @return 
	 * @version v1.0 
	 * 2013-3-11
	 */
	
	public Long insert(OperateLog operateLog) ;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日志插入
	 * @param operateLogBusinessStruct
	 * @return 
	 * @version v1.0 
	 * 2013-3-11
	 */
	public Long insert(OperateLogBusinessStruct operateLogBusinessStruct);
	
	 /**
     * 
     * @author 韩大年  
     * @Description:  批量插入日志方法
     * @param oList 
     * @version v1.0 
     * 2013-3-13
     */
    public void batchInsert(List<OperateLogBusinessStruct> oList);
		
	 /**
     * 
     * @author 韩大年  
     * @Description:  日志查询
     * @param operateLogVO
     * @param pagination
     * @return 
     * @version v1.0 
     * 2013-3-12
     */
    public Pagination selectOperateLogByPagination(OperateLogVO operateLogVO,Pagination pagination);
    /**
     * 某申请单日志查询
    * @author wyf  
    * @Description: 
    * @param: creditAppId
    * @param: pagination
    * @return: Pagination 
    * @throws:
     */
    public Pagination queryOperateLogByCreditId(String creditAppId,Pagination pagination);
  
}
