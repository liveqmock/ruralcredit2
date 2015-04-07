package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.OperateLog;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OperateLogVO;
/**
 * 
 * cs
 * Title:IOperateLogDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:22:36
 * @author wyf
 * @version v2.0
 */
public interface IOperateLogDAO  {
	 /**
	 * 
	 * @author 韩大年  
	 * @Description:  日志插入
	 * @param operateLog
	 * @return 
	 * @version v1.0 
	 * 2013-3-11
	 */
	Long insert(OperateLog operateLog);
    
	 /**
     * 
     * @author 韩大年  
     * @Description:  批量插入日志方法
     * @param oList 
     * @version v1.0 
     * 2013-3-13
     */
	void batchInsert(List<OperateLog> operateLogList);
    
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
	public Pagination selectOperateLogByPagination(OperateLogVO operateLogVO,Pagination pagination) ;
	/**
     * 某申请单日志查询
    * @author wyf  
    * @Description: 
    * @param: creditAppId
    * @param: pagination
    * @return: Pagination 
    * @throws:
     */
	public Pagination queryOperateLogByCreditId(String creditAppId,Pagination pagination) ;
}